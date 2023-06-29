package com.study.battleq.modules.user.controller.request;

import javax.validation.constraints.NotEmpty;

public record UserSignupRequest(
        @NotEmpty(message = "E-Mail은 필수입니다.")
        String email,
        @NotEmpty(message = "이름은 필수입니다.")
        String name,
        @NotEmpty(message = "비밀번호는 필수입니다.")
        String password,
        @NotEmpty(message = "닉네임은 필수입니다.")
        String nickname) {
}
