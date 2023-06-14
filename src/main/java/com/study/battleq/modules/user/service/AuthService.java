package com.study.battleq.modules.user.service;

import com.study.battleq.infrastructure.common.service.AuthenticationManagerService;
import com.study.battleq.infrastructure.config.jwt.JwtTokenProvider;
import com.study.battleq.infrastructure.config.properties.JwtProperties;
import com.study.battleq.modules.user.domain.redis.UserRedisRepository;
import com.study.battleq.modules.user.service.dto.TokenDto;
import com.study.battleq.modules.user.service.exception.LoginFailedException;
import com.study.battleq.modules.user.service.exception.RefreshTokenExpiredException;
import com.study.battleq.modules.user.service.usecase.LoginUseCase;
import com.study.battleq.modules.user.service.usecase.RefreshTokenUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class AuthService implements LoginUseCase, RefreshTokenUseCase {

    private final AuthenticationManagerService authenticationManagerService;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtProperties properties;
    private final UserRedisRepository userRedisRepository;


    @Override
    public TokenDto login(String email, String password) {
        Authentication authentication = getAuthentication(email, password);
        TokenDto tokenDto = jwtTokenProvider.createToken(authentication);
        saveRefreshToken(authentication.getName(), tokenDto);
        return tokenDto;
    }

    private void saveRefreshToken(String id, TokenDto tokenDto) {
        userRedisRepository.save(id, tokenDto.getRefreshToken(),
                properties.getRefreshTokenExpireTime(),
                TimeUnit.MILLISECONDS);
    }

    private Authentication getAuthentication(String email, String password) {
        try {
            return authenticationManagerService.getAuthentication(email, password);
        } catch (AuthenticationException e) {
            throw LoginFailedException.thrown();
        }
    }

    @Override
    public TokenDto refresh(String accessToken, String refreshToken) {
        jwtTokenProvider.isValidateToken(refreshToken);
        Authentication authentication = getAuthenticationByAccessToken(accessToken);
        String refreshTokenByRedis = userRedisRepository.findByEmail(authentication.getName()).orElseThrow(RefreshTokenExpiredException::thrown);
        if (!refreshTokenByRedis.equals(refreshToken)) {
            throw new IllegalArgumentException("RefreshToken이 일치하지 않습니다.");
        }
        TokenDto tokenDto = jwtTokenProvider.createToken(authentication);
        saveRefreshToken(authentication.getName(), tokenDto);
        return tokenDto;
    }

    private Authentication getAuthenticationByAccessToken(String accessToken) {
        try {
            return jwtTokenProvider.getAuthentication(accessToken);
        } catch (Exception e) {
            throw new IllegalArgumentException("올바른 토큰이 아닙니다.");
        }
    }
}
