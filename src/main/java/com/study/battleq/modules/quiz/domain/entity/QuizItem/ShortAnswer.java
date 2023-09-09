package com.study.battleq.modules.quiz.domain.entity.QuizItem;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("SHORT_ANSWER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ShortAnswer extends Quiz {

    private ShortAnswer(String question, String answer, String description) {
        super(question, answer, description);
    }

    public static ShortAnswer of(String question, String answer, String description) {
        return new ShortAnswer(question, answer, description);
    }
}
