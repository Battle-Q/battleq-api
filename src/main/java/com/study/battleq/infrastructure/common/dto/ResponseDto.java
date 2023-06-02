package com.study.battleq.infrastructure.common.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ResponseDto<T> {
    private T data;
    private HttpStatus status;

    public ResponseDto(T data, HttpStatus status) {
        this.data = data;
        this.status = status;
    }

    public static <T> ResponseDto<T> ok() {
        return new ResponseDto<T>(null, HttpStatus.OK);
    }

    public static <T> ResponseDto<T> ok(T data) {
        return new ResponseDto<T>(data, HttpStatus.OK);
    }

    public static <T> ResponseDto<T> ok(T data, HttpStatus status) {
        return new ResponseDto<T>(data, status);
    }


}
