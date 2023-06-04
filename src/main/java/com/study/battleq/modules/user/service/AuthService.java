package com.study.battleq.modules.user.service;

import com.study.battleq.infrastructure.common.service.AuthenticationManagerService;
import com.study.battleq.infrastructure.config.jwt.JwtTokenProvider;
import com.study.battleq.infrastructure.config.properties.JwtProperties;
import com.study.battleq.modules.user.domain.redis.RedisRepository;
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
    private final RedisRepository redisRepository;
    private static final String REFRESH_TOKEN_PREFIX = "REFRESH_TOKEN:";

    @Override
    public TokenDto login(String email, String password) {
        Authentication authentication = getAuthentication(email, password);
        TokenDto tokenDto = jwtTokenProvider.createToken(authentication);
        saveRefreshToken(authentication.getName(), tokenDto);
        return tokenDto;
    }

    private void saveRefreshToken(String id, TokenDto tokenDto) {
        redisRepository.save(REFRESH_TOKEN_PREFIX + id, tokenDto.getRefreshToken(),
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
        validateRefreshToken(refreshToken);
        return null;
    }

    private void validateRefreshToken(String refreshToken) {
        if (!jwtTokenProvider.isValidateToken(refreshToken)) {
            throw RefreshTokenExpiredException.thrown();
        }
    }
}
