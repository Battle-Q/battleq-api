package com.study.battleq.modules.board.service.dto.response;

import lombok.*;
import org.springframework.http.HttpStatus;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class BoardSearchResponse {
    private String title;
    private String content;
    private String category;
    private String writer;
    private int view;

    public static BoardSearchResponse of(String title, String content, String category, String writer, int view) {
        return new BoardSearchResponse(title, content, category, writer, view);
    }
}
