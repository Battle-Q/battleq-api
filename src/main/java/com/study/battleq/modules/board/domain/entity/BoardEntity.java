package com.study.battleq.modules.board.domain.entity;

import com.study.battleq.infrastructure.common.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Table(name = "boards")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardEntity extends BaseEntity {
    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;

    @Column(name = "category")
    private String category;

    @Column(name = "priority")
    private boolean priority;

    @Column(name = "writer")
    private String writer;

    @Column(name = "view")
    private int view;

    @Column(name = "userId")
    private Long userId;

    private BoardEntity(String title, String content, String category, boolean priority, String writer, Long userId) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.priority = priority;
        this.writer = writer;
        this.view = 0;
        this.userId = userId;
    }

    public void updateBoard(String content, String category, boolean priority){
        this.content = content;
        this.category = category;
        this.priority = priority;
    }
    public static BoardEntity of(String title, String content, String category, boolean priority, String writer, Long userId) {
        return new BoardEntity(title, content, category, priority, writer, userId);
    }
}
