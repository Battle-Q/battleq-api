package com.study.battleq.modules.user.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.study.battleq.modules.user.domain.entity.Authority;
import com.study.battleq.modules.user.domain.entity.UserEntity;
import com.study.battleq.modules.user.domain.repository.UserCommandService;
import com.study.battleq.modules.user.domain.repository.UserQueryService;
import com.study.battleq.modules.user.service.dto.UserResponseDto;
import com.study.battleq.modules.user.service.dto.UserSignupCommand;
import com.study.battleq.modules.user.service.exception.AlreadySignupException;
import com.study.battleq.modules.user.service.exception.AlreadyUsedNicknameException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

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
    when(userQueryService.hasDuplicateEmail(anyString())).thenReturn(true);
    //when
    //then
    assertThrows(AlreadySignupException.class, () -> userService.signup(UserSignupCommand.of("email", "name", "123", "nick")));
  }

  @Test
  void 닉네임_중복() {
    //given
    when(userQueryService.hasDuplicateEmail(anyString())).thenReturn(false);
    when(userQueryService.hasDuplicateNickname(anyString())).thenReturn(true);
    //when
    //then
    assertThrows(AlreadyUsedNicknameException.class, () -> userService.signup(UserSignupCommand.of("email", "name", "123", "nick")));
  }

  @Test
  void 가입_성공() {
    //given
    when(userQueryService.hasDuplicateEmail(anyString())).thenReturn(false);
    when(userQueryService.hasDuplicateNickname(anyString())).thenReturn(false);
    when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
    //when
    userService.signup(UserSignupCommand.of("email", "name", "123", "nick"));
    //then
    verify(userCommandService, times(1)).save(any());
  }

  @Test
  void 탈퇴_성공() {
    //given
    when(userQueryService.findById(anyLong())).thenReturn(UserEntity.of("email", "name", "password", "nick", Authority.ROLE_STUDENT));
    //when
    userService.withdraw(1L);
    //then
    verify(userCommandService, times(1)).save(any());
  }

  @Test
  void me_api_조회_성공() {
    //given
    when(userQueryService.findById(anyLong())).thenReturn(UserEntity.of("email", "name", "password", "nick", Authority.ROLE_STUDENT));
    //when
    UserResponseDto responseDto = userService.me(1L);
    //then
    assertEquals("name", responseDto.getName());
    assertEquals("nick", responseDto.getNickname());
    assertEquals(Authority.ROLE_STUDENT, responseDto.getAuthority());
    verify(userQueryService, times(1)).findById(anyLong());
  }
}