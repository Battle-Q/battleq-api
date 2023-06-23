package com.study.battleq.modules.user.service;

import com.study.battleq.modules.user.domain.entity.Authority;
import com.study.battleq.modules.user.domain.entity.UserEntity;
import com.study.battleq.modules.user.domain.repository.UserCommandService;
import com.study.battleq.modules.user.domain.repository.UserQueryService;
import com.study.battleq.modules.user.domain.repository.exception.UserNotFoundException;
import com.study.battleq.modules.user.service.dto.UserSignupCommand;
import com.study.battleq.modules.user.service.exception.AlreadySignupException;
import com.study.battleq.modules.user.service.exception.AlreadyUsedNicknameException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    UserService userService;
    @Mock
    UserQueryService userQueryService;
    @Mock
    UserCommandService userCommandService;
    @Mock
    PasswordEncoder passwordEncoder;

    @Test
    void 이미_가입한_회원() {
        //given
        when(userQueryService.findByEmail(anyString())).thenReturn(UserEntity.of("email","name","password","nick", Authority.ROLE_STUDENT));
        //when
        //then
        assertThrows(AlreadySignupException.class, () -> userService.signup(UserSignupCommand.of("email", "name", "123", "nick")));
    }

    @Test
    void 닉네임_중복() {
        //given
        when(userQueryService.findByEmail(anyString())).thenReturn(UserEntity.of("email","name","password","nick", Authority.ROLE_STUDENT));
        //when
        //then
        assertThrows(AlreadyUsedNicknameException.class, () -> userService.signup(UserSignupCommand.of("email", "name", "123", "nick")));
    }

    @Test
    void 가입_성공() {
        //given
        when(userQueryService.findByEmail(anyString())).thenThrow(UserNotFoundException.class);
        when(userQueryService.findByNickname(anyString())).thenThrow(UserNotFoundException.class);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        //when
        userService.signup(UserSignupCommand.of("email", "name", "123", "nick"));
        //then
        verify(userCommandService, times(1)).save(any());
    }
}