package com.study.battleq.modules.quiz.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class QuizDto {

    private Long id;

    private QuizType quizType;

    private Object quizData;



}
