package com.study.battleq.modules.quiz.domain.entity;

public enum QuizItemType {
    SHORT_ANSWER("단답형"),
    TRUE_OR_FALSE("OX퀴즈"),
    MULTIPLE_CHOICE("객관식"),
    INITIAL("초성퀴즈"),
    CATCH_MIND("캐치마인드"),
    MAKE_ORDER("순서맞추기");

    //생성자화
    private String description;

    QuizItemType(String quizType) {
        this.description = quizType;
    }
}
