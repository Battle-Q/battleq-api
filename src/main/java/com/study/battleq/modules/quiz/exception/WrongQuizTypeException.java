package com.study.battleq.modules.quiz.exception;

import com.study.battleq.infrastructure.common.exception.BattleQException;
import org.springframework.http.HttpStatus;

public class WrongQuizTypeException extends BattleQException {

    private static final WrongQuizTypeException INSTANCE = new WrongQuizTypeException("잘못된 퀴즈 타입입니다.");

    public WrongQuizTypeException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }

    public static WrongQuizTypeException thrown() {
        return INSTANCE;
    }
}
