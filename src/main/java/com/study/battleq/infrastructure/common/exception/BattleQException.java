package com.study.battleq.infrastructure.common.exception;

import org.springframework.http.HttpStatus;

public abstract class BattleQException extends RuntimeException {

    private HttpStatus status;

    public BattleQException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
