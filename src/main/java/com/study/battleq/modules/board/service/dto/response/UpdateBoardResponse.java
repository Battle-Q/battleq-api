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
    private String userNickname;
    private int views;
    private int likeCount;
    private int dislikeCount;
    private boolean isBest;

    public static UpdateBoardResponse of(Long id, String title, String content, String category, String writer, int views, int likeCount, int dislikeCount, boolean isBest) {
        return new UpdateBoardResponse(id, title, content, category, writer, views, likeCount, dislikeCount, isBest);
    }
}
