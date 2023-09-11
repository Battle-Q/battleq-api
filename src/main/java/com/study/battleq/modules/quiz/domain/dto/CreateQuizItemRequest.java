package com.study.battleq.modules.quiz.domain.dto;


import com.study.battleq.modules.quiz.domain.entity.QuizItemType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class CreateQuizItemRequest {

    //todo Long과 QuizItemType은 NotEmpty가 안되는데 이유 확인하기
    @NotNull(message = "퀴즈 id는 필수입니다.")
    private Long quizId;
    @NotNull(message = "quizType은 필수입니다.")
    private QuizItemType quizType;
    @NotEmpty(message = "quizName은 필수입니다.")
    private String quizName;
    @NotEmpty(message = "question은 필수입니다.")
    private String question;
    @NotEmpty(message = "answer은 필수입니다.")
    private String answer;
    private String description;
    private String image;


    private CreateQuizItemRequest(Long quizId, QuizItemType quizType, String quizName, String question, String answer, String description, String image) {
        this.quizId = quizId;
        this.quizType = quizType;
        this.quizName = quizName;
        this.question = question;
        this.answer = answer;
        this.description = description;
        this.image = image;
    }

    public static CreateQuizItemRequest of(Long quizId, QuizItemType quizType, String quizName, String question, String answer, String description, String image) {
        return new CreateQuizItemRequest(quizId, quizType, quizName, question, answer, description, image);
    }

}
