package com.study.battleq.modules.user.service;

import com.study.battleq.infrastructure.common.service.AuthenticationManagerService;
import com.study.battleq.infrastructure.config.jwt.JwtTokenProvider;
import com.study.battleq.modules.user.service.dto.TokenDto;
import com.study.battleq.modules.user.service.exception.LoginFailedException;
import com.study.battleq.modules.user.service.usecase.LoginUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements LoginUseCase {

    private final AuthenticationManagerService authenticationManagerService;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public TokenDto login(String email, String password) {
        Authentication authentication = getAuthentication(email, password);
        return jwtTokenProvider.createToken(authentication);
    }

    private Authentication getAuthentication(String email, String password) {
        try {
            return authenticationManagerService.getAuthentication(email, password);
        } catch (AuthenticationException e) {
            throw LoginFailedException.thrown();
        }
    }
}
