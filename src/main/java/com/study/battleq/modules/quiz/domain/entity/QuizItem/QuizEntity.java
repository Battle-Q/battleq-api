package com.study.battleq.modules.quiz.domain.entity.QuizItem;

import com.study.battleq.infrastructure.common.entity.BaseEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "quizzes")
public class QuizEntity extends BaseEntity {

    @OneToMany
    @JoinColumn(name = "quiz_set_id")
    private List<QuizItemEntity> quizItems;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "category")
    private String category;


    //todo 항목으로 user의 어떤 속성을 매핑할지? (id, email)
    @Column(name = "created_by", nullable = false)
    private String createdBy;

}