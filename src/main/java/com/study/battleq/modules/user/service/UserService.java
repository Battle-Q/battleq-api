package com.study.battleq.modules.user.service;

import com.study.battleq.modules.user.domain.entity.Authority;
import com.study.battleq.modules.user.domain.entity.UserEntity;
import com.study.battleq.modules.user.domain.repository.UserCommandService;
import com.study.battleq.modules.user.domain.repository.UserQueryService;
import com.study.battleq.modules.user.service.dto.UserResponseDto;
import com.study.battleq.modules.user.service.dto.UserSignupCommand;
import com.study.battleq.modules.user.service.exception.AlreadySignupException;
import com.study.battleq.modules.user.service.exception.AlreadyUsedNicknameException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserQueryService userQueryService;
  private final UserCommandService userCommandService;
  private final PasswordEncoder passwordEncoder;

  public void signup(UserSignupCommand command) {
    validateAlreadySignup(command.getEmail());
    validateNickname(command.getNickname());
    UserEntity entity = UserEntity.of(command.getEmail(), command.getName(), passwordEncoder.encode(command.getPassword()), command.getNickname(),
        Authority.ROLE_STUDENT);
    userCommandService.save(entity);
  }

  public void withdraw(Long id) {
    UserEntity entity = userQueryService.findById(id);
    entity.withdraw();
    userCommandService.save(entity);
  }

  public UserResponseDto me(Long userId) {
    UserEntity userEntity = userQueryService.findById(userId);
    return UserResponseDto.builder()
        .name(userEntity.getName())
        .nickname(userEntity.getNickname())
        .authority(userEntity.getAuthority())
        .build();
  }

  private void validateAlreadySignup(String email) {
    if (userQueryService.hasDuplicateEmail(email)) {
      throw AlreadySignupException.thrown();
    }
  }

  private void validateNickname(String nickname) {
    if (userQueryService.hasDuplicateNickname(nickname)) {
      throw AlreadyUsedNicknameException.thrown();
    }
  }
}
