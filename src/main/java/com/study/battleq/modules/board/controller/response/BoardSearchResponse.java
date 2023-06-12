package com.study.battleq.modules.board.controller.response;

import lombok.*;
import org.springframework.http.HttpStatus;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class BoardSearchResponse<T> {
    private LocalDateTime timeStamp;
    private HttpStatus httpStatus;
    private T data;
}