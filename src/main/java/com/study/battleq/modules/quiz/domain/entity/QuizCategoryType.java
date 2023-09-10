package com.study.battleq.modules.quiz.domain.entity;

public enum QuizCategoryType {
    IT("IT"),
    상식("상식"),
    경제("경제"),
    아무거나("아무거나");

    private String description;

    QuizCategoryType(String description) {
        this.description = description;
    }
}
