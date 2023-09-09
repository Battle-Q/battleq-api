package com.study.battleq.modules.quiz.domain.entity.QuizItem;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CATCH_MIND")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CatchMind extends Quiz {
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
