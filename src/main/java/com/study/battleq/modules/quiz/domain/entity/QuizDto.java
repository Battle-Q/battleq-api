package com.study.battleq.modules.quiz.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class QuizDto {

    @Getter
    private Long id;

    @Getter
    private QuizType quizType;

    @Getter
    private Object quizData;



}
