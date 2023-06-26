package com.study.battleq.modules.user.service.usecase;

import com.study.battleq.modules.user.service.dto.UserSignupCommand;

public interface UserSignupUseCase {

    void signup(UserSignupCommand command);
}
