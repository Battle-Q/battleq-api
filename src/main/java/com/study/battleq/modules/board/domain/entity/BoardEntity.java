package com.study.battleq.modules.board.domain.entity;

import com.study.battleq.infrastructure.common.entity.BaseEntity;
import com.study.battleq.modules.user.domain.entity.UserEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.EntityGraph;

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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;
    @Column(name = "views")
    private int views;
    @Column(name = "likeCount")
    private int likeCount;
    @Column(name = "dislikeCount")
    private int dislikeCount;
    @Column(name = "isBest")
    private boolean isBest;

    private BoardEntity(String title, String content, String category, UserEntity userEntity, int views, int likeCount, int dislikeCount, boolean isBest) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.user = userEntity;
        this.views = views;
        this.likeCount = likeCount;
        this.dislikeCount = dislikeCount;
        this.isBest = isBest;
    }

    public void updateBoard(String content, String category) {
        this.content = content;
        this.category = category;
    }

    public static BoardEntity createBoardEntity(String title, String content, String category, UserEntity userEntity) {
        return new BoardEntity(title, content, category, userEntity, 0, 0, 0, false);
    }

    public static BoardEntity of(String title, String content, String category, UserEntity userEntity, int views, int likeCount, int dislikeCount, boolean isBest) {
        return new BoardEntity(title, content, category, userEntity, views, likeCount, dislikeCount, isBest);
    }
}
