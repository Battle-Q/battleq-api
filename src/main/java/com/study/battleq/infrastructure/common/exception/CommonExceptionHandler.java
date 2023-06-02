package com.study.battleq.infrastructure.common.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(BattleQException.class)
    public ResponseEntity<?> battleQException(BattleQException e, HttpServletRequest request) {
        return ResponseEntity.status(e.getStatus())
                .body(new ErrorDto(e.getMessage(), request.getRequestURI(), request.getMethod()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        String message = e.getBindingResult()
                .getAllErrors()
                .get(0)
                .getDefaultMessage();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorDto(message, request.getRequestURI(), request.getMethod()));
    }


    @Getter
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    private static class ErrorDto {

        private String message;
        private String path;
        private String method;

    }
}
