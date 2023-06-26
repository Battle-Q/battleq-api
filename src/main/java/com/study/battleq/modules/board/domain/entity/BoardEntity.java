package com.study.battleq.modules.board.domain.entity;

import com.study.battleq.infrastructure.common.entity.BaseEntity;
import com.study.battleq.modules.user.domain.entity.UserEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "boards")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardEntity extends BaseEntity {
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "userId", nullable = false)
    private Long userId;

    @Column(name = "view")
    private int view;

    @Column(name = "like")
    private int like;

    @Column(name = "dislike")
    private int dislike;

    @Column(name = "isBest")
    private boolean isBest;

    private BoardEntity(String title, String content, String category, Long userId, int view, int like, int dislike, boolean isBest) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.userId = userId;
        this.view = view;
        this.like = like;
        this.dislike = dislike;
        this.isBest = isBest;
    }

    public void updateBoard(String content, String category) {
        this.content = content;
        this.category = category;
    }
    public static BoardEntity of(String title, String content, String category, Long userId) {
        return new BoardEntity(title, content, category, userId, 0, 0, 0, false);
    }
    public static BoardEntity of(String title, String content, String category, Long userId, int view, int like, int dislike, boolean isBest) {
        return new BoardEntity(title, content, category, userId, view, like, dislike, isBest);
    }
}
