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
    private String question;
    private String answer;
    private String description;

    private CreateQuizRequest(QuizType quizType, String question, String answer, String description) {
        this.quizType = quizType;
        this.question = question;
        this.answer = answer;
        this.description = description;
    }

    public static CreateQuizRequest of(QuizType quizType, String question, String answer, String description) {
        return new CreateQuizRequest(quizType, question, answer, description);
    }

}
