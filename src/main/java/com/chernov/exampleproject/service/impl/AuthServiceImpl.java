package com.chernov.exampleproject.service.impl;

import com.chernov.exampleproject.jwt.JwtUtil;
import com.chernov.exampleproject.model.auth.AuthRequest;
import com.chernov.exampleproject.model.auth.TokenResponse;
import com.chernov.exampleproject.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public TokenResponse authenticate(AuthRequest authRequest) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getEmail());
        boolean matchesPass = bCryptPasswordEncoder.matches(authRequest.getPassword(), userDetails.getPassword());
        if (!matchesPass) {
            throw new AccessDeniedException("Wrong password");
        }
        String token = jwtUtil.generateToken(userDetails.getUsername());

        return new TokenResponse(token);
    }
}
