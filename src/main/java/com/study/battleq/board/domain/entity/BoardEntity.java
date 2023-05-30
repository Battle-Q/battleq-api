package com.study.battleq.board.domain.entity;

import com.study.battleq.infrastructure.common.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Getter
@Table(name = "boards")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardEntity extends BaseEntity {
    private String title;

    private String content;

    private String category;

    private boolean priority;

    private String writer;

    private int view;

    @Builder
    private BoardEntity(String title, String content, String category, boolean priority, String writer) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.priority = priority;
        this.writer = writer;
        this.view = 0;
    }
}
