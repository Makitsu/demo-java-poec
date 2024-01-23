package com.jmd.poec.java.demojpa2.configuration;

import com.jmd.poec.java.demojpa2.domain.entity.User;
import com.jmd.poec.java.demojpa2.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private JwtService jwtService;
    private PasswordEncoder passwordEncoder;

    public JwtFilter(JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if(Objects.equals("/users/authenticate",request.getRequestURI())){
            filterChain.doFilter(request,response);
            return;
        }

        String token = jwtService.retrieveToken(request);

        User user = jwtService.validateAndReturnUsername(token)
                .orElseThrow(() -> new AuthorizationServiceException("User not found !"));

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                user.getUsername(),
                passwordEncoder.encode(user.getPassword()),
                new ArrayList<>()
        );

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request,response);

    }
}
