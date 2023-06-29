package com.study.battleq.modules.user.controller.request;

import javax.validation.constraints.NotEmpty;

public record RefreshTokenRequest(
        @NotEmpty(message = "AccessToken은 필수입니다.")
        String accessToken,
        @NotEmpty(message = "RefreshToken은 필수입니다.")
        String refreshToken) {
}
