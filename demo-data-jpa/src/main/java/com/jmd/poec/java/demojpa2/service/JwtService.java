package com.jmd.poec.java.demojpa2.service;

import com.jmd.poec.java.demojpa2.domain.entity.User;
import com.jmd.poec.java.demojpa2.repository.UserRepository;
import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;


@Service
public class JwtService {

    Logger LOG = LoggerFactory.getLogger(JwtService.class);

    @Value("${jwt.expiration}")
    private Long expirationDelta;

    @Value("${jwt.secret}")
    private String secret;

    private final UserRepository userRepository;

    public JwtService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String generateToken(String username){

        Date expirationDate = Date.from(Instant.now().plus(expirationDelta, ChronoUnit.MILLIS));

        String token = Jwts.builder()
                .setExpiration(expirationDate)
                .setIssuedAt(new Date())
                .setSubject(username)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();


        LOG.info("Generated token for user {} : {}",username,token);

        return token;
    }

    public String retrieveToken(HttpServletRequest request){

        String authorizationHeader = request.getHeader("Authorization");

        if(Strings.isBlank(authorizationHeader)){
            throw new AuthorizationServiceException("Token not found !");
        }

        if(!authorizationHeader.startsWith("Bearer")){
            throw new AuthorizationServiceException("Invalid auth !");
        }

        return authorizationHeader.substring(7);

    }

    public Optional<User> validateAndReturnUsername(String token){

        JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(secret).build();

        Claims body = jwtParser.parseClaimsJws(token).getBody();

        if(Objects.isNull(body.getSubject())){
            throw new AuthorizationServiceException("Empty token subject !");
        }

        String subject = body.getSubject();

        if(Strings.isBlank(subject)){
            throw new AuthorizationServiceException("Empty token subject !");
        }

        if(!userRepository.existsByUsername(subject)){
            throw new AuthorizationServiceException("Username not found !");
        }

        return userRepository.findFirstByUsername(subject);

    }
}
