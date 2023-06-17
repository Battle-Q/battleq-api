package com.study.battleq.modules.user.service.exception;

import com.study.battleq.infrastructure.common.exception.BattleQException;
import org.springframework.http.HttpStatus;

public class AlreadyUserSignupException extends BattleQException {

    private static final AlreadyUserSignupException INSTANCE = new AlreadyUserSignupException("이미 회원가입한 계정입니다.", HttpStatus.BAD_REQUEST);

    public AlreadyUserSignupException(String message, HttpStatus status) {
        super(message, status);
    }

    public static AlreadyUserSignupException thrown() {
        return INSTANCE;
    }
}
