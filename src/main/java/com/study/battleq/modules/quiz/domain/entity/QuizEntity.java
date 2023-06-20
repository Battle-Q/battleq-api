package com.study.battleq.modules.quiz.domain.entity;

import com.study.battleq.infrastructure.common.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "quizzes")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuizEntity extends BaseEntity {

    @Column(name="quiz_type", nullable = false)
    private QuizType quizType;

    //todo 타입 생각하기 object 타입은 h2 column 타입 에러
    @Column(name = "quiz_data", nullable = false)
    private String quizData;



    private QuizEntity(QuizType quizType, String quizData) {
        this.quizType = quizType;
        this.quizData = quizData;
    }


    public static QuizEntity of(QuizType quizType, String quizData) {
       return new QuizEntity(quizType, quizData);
    }


    /**
     * jpa 상속 매핑전략
     * json 깨지면 이슈 생길듯
     *
     * 퀴즈, 답
     *
     * 퀴즈 여러개
     */

}
