package com.study.battleq.modules.user.service;

import com.study.battleq.modules.user.domain.entity.Authority;
import com.study.battleq.modules.user.domain.entity.UserEntity;
import com.study.battleq.modules.user.domain.repository.UserCommandService;
import com.study.battleq.modules.user.domain.repository.UserQueryService;
import com.study.battleq.modules.user.domain.repository.exception.UserNotFoundException;
import com.study.battleq.modules.user.service.dto.UserSignupCommand;
import com.study.battleq.modules.user.service.exception.AlreadySignupException;
import com.study.battleq.modules.user.service.exception.AlreadyUsedNicknameException;
import com.study.battleq.modules.user.service.usecase.UserSignupUseCase;
import com.study.battleq.modules.user.service.usecase.UserWithdrawUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserSignupUseCase, UserWithdrawUseCase {

  private final UserQueryService userQueryService;
  private final UserCommandService userCommandService;
  private final PasswordEncoder passwordEncoder;

  @Override
  public void signup(UserSignupCommand command) {
    validateAlreadySignup(command.getEmail());
    validateNickname(command.getNickname());
    UserEntity entity = UserEntity.of(command.getEmail(), command.getName(), passwordEncoder.encode(command.getPassword()), command.getNickname(),
        Authority.ROLE_STUDENT);
    userCommandService.save(entity);
  }

  @Override
  public void withdraw(Long id) {
    UserEntity entity = userQueryService.findById(id);
    entity.withdraw();
    userCommandService.save(entity);
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
