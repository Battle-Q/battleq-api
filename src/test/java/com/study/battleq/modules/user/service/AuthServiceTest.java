package com.study.battleq.modules.user.service;

import com.study.battleq.infrastructure.common.service.AuthenticationManagerService;
import com.study.battleq.infrastructure.config.jwt.JwtTokenProvider;
import com.study.battleq.modules.user.service.dto.TokenDto;
import com.study.battleq.modules.user.service.exception.LoginFailedException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.BadCredentialsException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @InjectMocks
    AuthService authService;
    @Mock
    AuthenticationManagerService authenticationManagerService;
    @Mock
    JwtTokenProvider jwtTokenProvider;

    @Test
    void 로그인_처리() {
        //given
        when(authenticationManagerService.getAuthentication(anyString(), anyString())).thenReturn(null);
        when(jwtTokenProvider.createToken(any())).thenReturn(TokenDto.of("a", "b"));
        //when
        TokenDto tokenDto = authService.login("email", "password");
        //then
        assertEquals("a", tokenDto.getAccessToken());
        assertEquals("b", tokenDto.getRefreshToken());
    }

    @Test
    void 로그인_실패() {
        //given
        when(authenticationManagerService.getAuthentication(anyString(), anyString())).thenThrow(BadCredentialsException.class);
        //when
        //then
        assertThrows(LoginFailedException.class, () -> authService.login("email", "password"));
    }
}