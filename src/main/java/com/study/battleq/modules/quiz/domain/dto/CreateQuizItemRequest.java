package com.study.battleq.modules.quiz.domain.dto;


import com.study.battleq.modules.quiz.domain.entity.QuizItemType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
public class CreateQuizItemRequest {

    //todo validation 추가
    @NotEmpty(message = "quizType은 필수입니다.")
    private QuizItemType quizType;
    @NotEmpty(message = "question은 필수입니다.")
    private String question;
    @NotEmpty(message = "answer은 필수입니다.")
    private String answer;
    private String description;
    private String image;

    private CreateQuizItemRequest(QuizItemType quizType, String question, String answer, String description, String image) {
        this.quizType = quizType;
        this.question = question;
        this.answer = answer;
        this.description = description;
        this.image =image;
    }

    public static CreateQuizItemRequest of(QuizItemType quizType, String question, String answer, String description, String image) {
        return new CreateQuizItemRequest(quizType, question, answer, description, image);
    }

}
