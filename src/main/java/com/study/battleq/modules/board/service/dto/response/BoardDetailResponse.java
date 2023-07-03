package com.study.battleq.modules.board.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
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

    private BoardDetailResponse(Long id, String title, String content, String category, Long userId, String userNickname, int views, int likeCount, int dislikeCount, boolean isBest) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.category = category;
        this.userId = userId;
        this.userNickname = userNickname;
        this.views = views;
        this.likeCount = likeCount;
        this.dislikeCount = dislikeCount;
        this.isBest = isBest;
    }

    public static BoardDetailResponse of(Long id, String title, String content, String category, Long userId, String userNickname, int views, int likeCount, int dislikeCount, boolean isBest) {
        return new BoardDetailResponse(id, title, content, category, userId, userNickname, views, likeCount, dislikeCount, isBest);
    }
}
