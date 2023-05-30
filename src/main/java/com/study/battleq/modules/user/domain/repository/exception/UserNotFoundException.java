package com.study.battleq.modules.user.domain.repository.exception;

import com.study.battleq.infrastructure.common.exception.BattleQException;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends BattleQException {

    private static final UserNotFoundException INSTANCE = new UserNotFoundException("해당 회원이 존재하지 않습니다.");

    public UserNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }

    public static UserNotFoundException thrown() {
        return INSTANCE;
    }
}
