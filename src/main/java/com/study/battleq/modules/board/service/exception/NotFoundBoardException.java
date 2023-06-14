package com.study.battleq.modules.board.service.exception;

import com.study.battleq.infrastructure.common.exception.BattleQException;
import org.springframework.http.HttpStatus;

public class NotFoundBoardException extends BattleQException {
    private static final NotFoundBoardException INSTANCE = new NotFoundBoardException("게시글을 찾을 수 없습니다.", HttpStatus.INTERNAL_SERVER_ERROR);

    public NotFoundBoardException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }

    public static NotFoundBoardException thrown() {
        return INSTANCE;
    }
}
