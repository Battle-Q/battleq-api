package com.study.battleq.modules.user.service;

import com.study.battleq.modules.user.service.dto.UserSignupCommand;
import com.study.battleq.modules.user.service.usecase.UserSignupUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserSignupUseCase {
    @Override
    public void signup(UserSignupCommand command) {

    }
}
