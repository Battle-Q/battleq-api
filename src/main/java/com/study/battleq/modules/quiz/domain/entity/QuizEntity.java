package com.study.battleq.modules.quiz.domain.entity;

import com.study.battleq.infrastructure.common.entity.BaseEntity;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "quizzes")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class QuizEntity extends BaseEntity {

    private QuizType quizType;
    private Object quizData;

    /**
     * jpa 상속 매핑전략
     * json 깨지면 좆댐
     *
     * 퀴즈, 답
     *
     * 퀴즈 여러개
     */

}
