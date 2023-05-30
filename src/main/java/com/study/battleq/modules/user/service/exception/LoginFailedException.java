package com.study.battleq.modules.user.service.exception;

import com.study.battleq.infrastructure.common.exception.BattleQException;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;

public class LoginFailedException extends BattleQException {

    private static final LoginFailedException INSTANCE = new LoginFailedException("로그인 실패, 아이디나 비밀번호를 확인해주세요.", HttpStatus.UNAUTHORIZED);

    public LoginFailedException(String message, HttpStatus status) {
        super(message, status);
    }

    public static LoginFailedException thrown() {
        return INSTANCE;
    }
}
