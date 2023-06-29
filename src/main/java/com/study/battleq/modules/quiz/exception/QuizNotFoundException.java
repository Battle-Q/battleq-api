package com.study.battleq.modules.quiz.exception;

import com.study.battleq.infrastructure.common.exception.BattleQException;
import org.springframework.http.HttpStatus;

public class QuizNotFoundException extends BattleQException {

    private static final QuizNotFoundException INSTANCE = new QuizNotFoundException("해당하는 퀴즈가 존재하지 않습니다.");

    public QuizNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }

    public static QuizNotFoundException thrown() {
        return INSTANCE;
    }
}
