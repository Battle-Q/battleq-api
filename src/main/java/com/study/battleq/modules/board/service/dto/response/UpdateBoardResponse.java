package com.study.battleq.modules.board.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class UpdateBoardResponse {
    private Long id;
    private String title;
    private String content;
    private String category;
    private String writer;
    private int view;
    private LocalDateTime updatedAt;

    public static UpdateBoardResponse of(Long id, String title, String content, String category, String writer, int view, LocalDateTime updatedAt) {
        return new UpdateBoardResponse(id, title, content, category, writer, view, updatedAt);
    }
}
