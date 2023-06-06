package com.study.battleq.modules.user.service;

import com.study.battleq.infrastructure.common.service.AuthenticationManagerService;
import com.study.battleq.infrastructure.config.jwt.JwtTokenProvider;
import com.study.battleq.infrastructure.config.properties.JwtProperties;
import com.study.battleq.modules.user.domain.redis.UserRedisRepository;
import com.study.battleq.modules.user.service.dto.TokenDto;
import com.study.battleq.modules.user.service.exception.LoginFailedException;
import com.study.battleq.modules.user.service.exception.RefreshTokenExpiredException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @InjectMocks
    AuthService authService;
    @Mock
    AuthenticationManagerService authenticationManagerService;
    @Mock
    JwtTokenProvider jwtTokenProvider;
    @Mock
    JwtProperties properties;
    @Mock
    UserRedisRepository userRedisRepository;

    @Test
    void 로그인_처리() {
        //given
        List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
        UserDetails principal = new User("1", "", authorities);
        Authentication authentication = new UsernamePasswordAuthenticationToken(principal, "", authorities);
        when(authenticationManagerService.getAuthentication(anyString(), anyString())).thenReturn(authentication);
        when(jwtTokenProvider.createToken(any())).thenReturn(TokenDto.of("a", "b"));
        when(properties.getRefreshTokenExpireTime()).thenReturn(10L);
        //when
        TokenDto tokenDto = authService.login("email", "password");
        //then
        assertEquals("a", tokenDto.getAccessToken());
        assertEquals("b", tokenDto.getRefreshToken());
        verify(userRedisRepository, times(1)).save(anyString(), anyString(), anyLong(), any());
    }

    @Test
    void 로그인_실패() {
        //given
        when(authenticationManagerService.getAuthentication(anyString(), anyString())).thenThrow(BadCredentialsException.class);
        //when
        //then
        assertThrows(LoginFailedException.class, () -> authService.login("email", "password"));
    }

    @Test
    void 리프레쉬_토큰_정상_발급() {
        //given
        List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
        UserDetails principal = new User("1", "", authorities);
        Authentication authentication = new UsernamePasswordAuthenticationToken(principal, "", authorities);
        when(jwtTokenProvider.isValidateToken(anyString())).thenReturn(true);
        when(jwtTokenProvider.getAuthentication(anyString())).thenReturn(authentication);
        when(userRedisRepository.findByUserId(anyString())).thenReturn(Optional.of("REFRESH_TOKEN"));
        when(jwtTokenProvider.createToken(any())).thenReturn(TokenDto.of("a", "b"));
        //when
        TokenDto tokenDto = authService.refresh("access", "REFRESH_TOKEN");
        //then
        assertEquals("a", tokenDto.getAccessToken());
        assertEquals("b", tokenDto.getRefreshToken());
        verify(userRedisRepository, times(1)).save(anyString(), anyString(), anyLong(), any());
    }

    @Test
    void 리프레쉬_토큰이_일치하지_않을_때() {
        //given
        List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
        UserDetails principal = new User("1", "", authorities);
        Authentication authentication = new UsernamePasswordAuthenticationToken(principal, "", authorities);
        when(jwtTokenProvider.isValidateToken(anyString())).thenReturn(true);
        when(jwtTokenProvider.getAuthentication(anyString())).thenReturn(authentication);
        when(userRedisRepository.findByUserId(anyString())).thenReturn(Optional.of("REFRESH_TOKEN"));
        //when
        //then
        assertThrows(IllegalArgumentException.class, () -> authService.refresh("access", "REFRESH_TOKEN1"));
    }

    @Test
    void 리프레쉬_토큰이_만료_되었을_때() {
        //given
        List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
        UserDetails principal = new User("1", "", authorities);
        Authentication authentication = new UsernamePasswordAuthenticationToken(principal, "", authorities);
        when(jwtTokenProvider.isValidateToken(anyString())).thenReturn(true);
        when(jwtTokenProvider.getAuthentication(anyString())).thenReturn(authentication);
        when(userRedisRepository.findByUserId(anyString())).thenReturn(Optional.empty());
        //when
        //then
        assertThrows(RefreshTokenExpiredException.class, () -> authService.refresh("access", "refresh"));
    }
}