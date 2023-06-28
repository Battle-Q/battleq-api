package com.study.battleq.modules.board.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BoardDetailResponse {
    private Long id;
    private String title;
    private String content;
    private String category;
    private Long userId;
    private String userNickname;
    private int views;
    private int likeCount;
    private int dislikeCount;
    private boolean isBest;

    public static BoardDetailResponse of(Long id, String title, String content, String category, Long userId, String userNickname, int views, int likeCount, int dislikeCount, boolean isBest) {
        return new BoardDetailResponse(id, title, content, category, userId, userNickname, views, likeCount, dislikeCount, isBest);
    }
}
