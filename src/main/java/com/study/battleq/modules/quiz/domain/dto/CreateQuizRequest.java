package com.study.battleq.modules.quiz.domain.dto;


import com.study.battleq.modules.quiz.domain.entity.QuizType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class CreateQuizRequest {

    @NotNull
    private QuizType quizType;
    @NotNull
    private String quizData;

    private CreateQuizRequest(QuizType quizType, String quizData) {
        this.quizType = quizType;
        this.quizData = quizData;
    }

    public static CreateQuizRequest of(QuizType quizType, String quizData){
        return new CreateQuizRequest(quizType, quizData);
    }

}
