package com.study.battleq.board.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class BoardSearchResponse<T> {
    private LocalDateTime timeStamp;
    private HttpStatus httpStatus;
    private T data;
}
