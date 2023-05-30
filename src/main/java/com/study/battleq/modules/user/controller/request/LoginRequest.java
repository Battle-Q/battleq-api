package com.study.battleq.modules.user.controller.request;

import javax.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank(message = "e-mail은 필수입니다.")
        String email,
        @NotBlank(message = "비밀번호는 필수입니다.")
        String password
) {
}
