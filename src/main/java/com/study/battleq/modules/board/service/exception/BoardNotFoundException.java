package com.study.battleq.modules.board.service.exception;

import com.study.battleq.infrastructure.common.exception.BattleQException;
import org.springframework.http.HttpStatus;

public class BoardNotFoundException extends BattleQException {
    private static final BoardNotFoundException INSTANCE = new BoardNotFoundException("게시글을 찾을 수 없습니다.", HttpStatus.BAD_REQUEST);

    public BoardNotFoundException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }

    public static BoardNotFoundException thrown() {
        return INSTANCE;
    }
}
