package com.study.battleq.modules.quiz.exception;

import com.study.battleq.infrastructure.common.exception.BattleQException;
import org.springframework.http.HttpStatus;

public class NoneQuizTypeException extends BattleQException {

    private static final NoneQuizTypeException INSTANCE = new NoneQuizTypeException("퀴즈 타입을 입력해주세요.");

    public NoneQuizTypeException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }

    public static NoneQuizTypeException thrown() {
        return INSTANCE;
    }
}
