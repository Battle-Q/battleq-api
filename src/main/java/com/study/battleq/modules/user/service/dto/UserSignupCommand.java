package com.study.battleq.modules.user.service.dto;

import lombok.Getter;

@Getter
public class UserSignupCommand {

    private String email;
    private String name;
    private String password;
    private String nickname;

    private UserSignupCommand(String email, String name, String password, String nickname) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.nickname = nickname;
    }

    public static UserSignupCommand of(String email, String name, String password, String nickname) {
        return new UserSignupCommand(email, name, password, nickname);
    }
}
