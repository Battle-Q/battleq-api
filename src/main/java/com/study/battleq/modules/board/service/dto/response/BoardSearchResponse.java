package com.study.battleq.modules.board.service.dto.response;

import lombok.*;
import org.springframework.http.HttpStatus;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Getter
public class BoardSearchResponse {
    private Long id;
    private String title;
    private String content;
    private String category;
    private String userNickname;
    private int views;
    private int likeCount;
    private int dislikeCount;
    private boolean isBest;

    // private boolean isRecent;
    private BoardSearchResponse(Long id, String title, String content, String category, String userNickname, int views, int likeCount, int dislikeCount, boolean isBest) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.category = category;
        this.userNickname = userNickname;
        this.views = views;
        this.likeCount = likeCount;
        this.dislikeCount = dislikeCount;
        this.isBest = isBest;
    }

    public static BoardSearchResponse of(Long id, String title, String content, String category, String userNickname, int views, int likeCount, int dislikeCount, boolean isBest) {
        return new BoardSearchResponse(id, title, content, category, userNickname, views, likeCount, dislikeCount, isBest);
    }
}
