package com.study.battleq.modules.quiz.domain.entity.QuizItem;

import com.study.battleq.infrastructure.common.entity.BaseEntity;
import com.study.battleq.modules.quiz.domain.dto.CreateQuizRequest;
import com.study.battleq.modules.quiz.domain.entity.QuizCategoryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "quizzes")
@Builder
@Getter
@AllArgsConstructor
public class QuizEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quiz_id")
    private Long id;

    @OneToMany
    @JoinColumn(name = "quiz_id")
    private List<QuizItemEntity> quizItems;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private QuizCategoryType category;


    //todo 항목으로 user의 어떤 속성을 매핑할지? (id, email)
    @Column(name = "created_by", nullable = false)
    private String createdBy;

    static public QuizEntity from(CreateQuizRequest createQuizRequest) {
        return QuizEntity.builder()
                .title(createQuizRequest.getTitle())
                .category(createQuizRequest.getCategory())
                .description(createQuizRequest.getDescription())
                .createdBy("")
                .build();
    }

    public QuizEntity() {

    }
}