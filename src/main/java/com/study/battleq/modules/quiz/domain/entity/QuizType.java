package com.study.battleq.modules.quiz.domain.entity;

public enum QuizType {
    CATCH_MIND("캐치마인드"),
    SHORT_ANSWER("단답형"),
    MULTIPLE_CHOICE("객관식"),
    TRUE_OR_FALSE("OX퀴즈"),
    MAKE_ORDER("순서맞추기"),
    INITIAL("초성퀴즈");

    //생성자화
    private String description;

    QuizType(String quizType) {
        this.description = quizType;
    }
}
