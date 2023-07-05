package com.study.battleq.modules.quiz.domain.entity;

import com.study.battleq.infrastructure.common.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Table(name = "quizz_options")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuizOptionEntity extends BaseEntity {

    @Column
    private Long quizId;

    @Column(name = "option")
    private String option;



    /**
     * jpa 상속 매핑전략
     * json 깨지면 이슈 생길듯
     *
     * 퀴즈, 답
     *
     * 퀴즈 여러개
     */

}
