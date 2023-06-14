package com.study.battleq.modules.user.service.usecase;

import com.study.battleq.modules.user.service.dto.TokenDto;

public interface RefreshTokenUseCase {
    TokenDto refresh(String accessToken, String refreshToken);
}
