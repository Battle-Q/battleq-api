package com.study.battleq.modules.user.service.exception;

import com.study.battleq.infrastructure.common.exception.BattleQException;
import org.springframework.http.HttpStatus;

public class AlreadySignupException extends BattleQException {

    private static final AlreadySignupException INSTANCE = new AlreadySignupException("이미 회원가입한 계정입니다.", HttpStatus.BAD_REQUEST);

    public AlreadySignupException(String message, HttpStatus status) {
        super(message, status);
    }

    public static AlreadySignupException thrown() {
        return INSTANCE;
    }
}
