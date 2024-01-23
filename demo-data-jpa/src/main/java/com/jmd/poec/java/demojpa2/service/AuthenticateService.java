package com.jmd.poec.java.demojpa2.service;

import com.jmd.poec.java.demojpa2.domain.dto.AuthenticateDTO;
import com.jmd.poec.java.demojpa2.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthenticateService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public AuthenticateService(UserRepository userRepository, JwtService jwtService, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    public String authenticate(AuthenticateDTO input) {

        if(Objects.isNull(input.getUsername())){
            throw new UsernameNotFoundException("Invalid user ");
        }

        if(!userRepository.existsByUsername(input.getUsername())){
            throw new UsernameNotFoundException("User not found from input "+input);
        }

        String token = jwtService.generateToken(input.getUsername());

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getUsername(),
                        passwordEncoder.encode(input.getPassword())
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return token;
    }
}
