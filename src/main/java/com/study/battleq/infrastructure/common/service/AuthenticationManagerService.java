package com.study.battleq.infrastructure.common.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationManagerService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public Authentication getAuthentication(String username, String password) {
        return authenticationManagerBuilder.getObject().authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }
}
