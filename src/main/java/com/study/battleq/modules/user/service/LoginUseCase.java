package com.study.battleq.modules.user.service;

import com.study.battleq.modules.user.service.dto.TokenDto;

public interface LoginUseCase {
    TokenDto login(String email, String password);
}
