package com.study.battleq.modules.quiz.domain.entity;

import com.study.battleq.infrastructure.common.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("CATCH_MIND")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CatchMind extends QuizEntity {
    @Column(name = "image")
    private String image;

    private CatchMind(String question, String answer, String description, String image) {
        super(question, answer, description);
        this.image = image;
    }

    public static CatchMind of(String question, String answer, String description, String image) {
        return new CatchMind(question, answer, description, image);
    }

}
