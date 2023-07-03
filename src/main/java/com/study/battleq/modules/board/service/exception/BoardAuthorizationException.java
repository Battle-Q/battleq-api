package com.study.battleq.modules.board.service.exception;

import com.study.battleq.infrastructure.common.exception.BattleQException;
import org.springframework.http.HttpStatus;

public class BoardAuthorizationException extends BattleQException {
    private static final BoardAuthorizationException INSTANCE = new BoardAuthorizationException("허가되지 않은 사용자 입니다.", HttpStatus.BAD_REQUEST);

    public BoardAuthorizationException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }

    public static BoardAuthorizationException thrown() {
        return INSTANCE;
    }
}
