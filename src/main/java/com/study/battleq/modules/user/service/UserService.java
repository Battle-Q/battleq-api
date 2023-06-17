package com.study.battleq.modules.user.service;

import com.study.battleq.modules.user.domain.entity.Authority;
import com.study.battleq.modules.user.domain.entity.UserEntity;
import com.study.battleq.modules.user.domain.repository.UserCommandService;
import com.study.battleq.modules.user.domain.repository.UserQueryService;
import com.study.battleq.modules.user.domain.repository.exception.UserNotFoundException;
import com.study.battleq.modules.user.service.dto.UserSignupCommand;
import com.study.battleq.modules.user.service.exception.AlreadyUserSignupException;
import com.study.battleq.modules.user.service.usecase.UserSignupUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserSignupUseCase {

    private final UserQueryService userQueryService;
    private final UserCommandService userCommandService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void signup(UserSignupCommand command) {
        validateUserAlreadySignup(command.getEmail());
        UserEntity entity = UserEntity.of(command.getEmail(), command.getName(), passwordEncoder.encode(command.getPassword()), command.getNickname(), Authority.ROLE_STUDENT);
        userCommandService.save(entity);
    }

    private void validateUserAlreadySignup(String email) {
        try {
            userQueryService.findByEmail(email);
        } catch (UserNotFoundException e) {
            return;
        }

        throw AlreadyUserSignupException.thrown();
    }
}