package com.study.battleq.infrastructure.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public abstract class BattleQException extends RuntimeException {

    @Getter
    private HttpStatus status;

    public BattleQException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
