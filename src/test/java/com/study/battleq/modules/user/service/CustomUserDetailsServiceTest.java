package com.study.battleq.modules.user.service;

import com.study.battleq.modules.user.domain.entity.Authority;
import com.study.battleq.modules.user.domain.entity.UserEntity;
import com.study.battleq.modules.user.domain.repository.UserQueryService;
import com.study.battleq.modules.user.domain.repository.exception.UserNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomUserDetailsServiceTest {

    @InjectMocks
    CustomUserDetailsService customUserDetailsService;
    @Mock
    UserQueryService userQueryService;

    @Test
    @DisplayName("유저가 정상 조회될 때 UserDetails가 생성된다.")
    void 유저_정상_조회_시_UserDetails_생성() {
        //given
        String email = "email";
        when(userQueryService.findByEmail(anyString())).thenReturn(UserEntity.of(email, "이름", "pwd", "nick", Authority.ROLE_ADMIN));
        //when
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
        //then
        assertEquals(email, userDetails.getUsername());
        assertEquals("pwd", userDetails.getPassword());
    }

    @Test
    @DisplayName("유저 조회가 되지 않을 때 Exception 발생")
    void 유저_조회가_안될_때_Exception_발생() {
        //given
        when(userQueryService.findByEmail(anyString())).thenThrow(UserNotFoundException.class);
        //when
        //then
        assertThrows(UserNotFoundException.class, () -> customUserDetailsService.loadUserByUsername("email"));
    }
}