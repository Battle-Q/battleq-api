package com.study.battleq.modules.user.service.exception;

import com.study.battleq.infrastructure.common.exception.BattleQException;
import org.springframework.http.HttpStatus;

public class AlreadyUsedNicknameException extends BattleQException {

    private static final AlreadyUsedNicknameException INSTANCE = new AlreadyUsedNicknameException("이미 사용중인 닉네임입니다.", HttpStatus.BAD_REQUEST);

    public AlreadyUsedNicknameException(String message, HttpStatus status) {
        super(message, status);
    }

    public static AlreadyUsedNicknameException thrown() {
        return INSTANCE;
    }
}
