package com.study.battleq.modules.quiz.domain;


import com.study.battleq.modules.quiz.domain.entity.QuizType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class CreateQuizResponse {

    @NotNull
    private QuizType quizType;
    @NotNull
    private String quizData;

    private CreateQuizResponse(QuizType quizType, String quizData) {
        this.quizType = quizType;
        this.quizData = quizData;
    }

    public static CreateQuizResponse of(QuizType quizType, String quizData) {
        return new CreateQuizResponse(quizType, quizData);
    }

}
