package com.study.battleq.modules.quiz.domain.dto;

import com.study.battleq.modules.quiz.domain.entity.QuizCategoryType;
import com.study.battleq.modules.quiz.domain.entity.QuizItem.QuizEntity;
import lombok.Builder;
import java.time.LocalDateTime;

@Builder
public class CreateQuizResponse {

    private Long id;

    private String title;

    private String description;

    //    @NotEmpty(message = "category 를 선택해주세요.")
    private QuizCategoryType category;

    private String createdBy;

    private LocalDateTime createdAt;

    static public CreateQuizResponse from(QuizEntity quizEntity){
        return CreateQuizResponse.builder()
                .id(quizEntity.getId())
                .title(quizEntity.getTitle())
                .description(quizEntity.getDescription())
                .category(quizEntity.getCategory())
                .createdBy(quizEntity.getCreatedBy())
                .createdAt(quizEntity.getCreatedAt())
                .build();
    }
}
