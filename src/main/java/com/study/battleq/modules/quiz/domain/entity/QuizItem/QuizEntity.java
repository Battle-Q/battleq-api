package com.study.battleq.modules.quiz.domain.entity.QuizItem;

import com.study.battleq.infrastructure.common.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
//@Table(name = "quizzes")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "QUIZ_TYPE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuizEntity extends BaseEntity {

//    @Column(name="quiz_type", nullable = false)
//    private QuizType quizType;

    @Column(name = "question", nullable = false)
    private String question;

    @Column(name = "answer", nullable = false)
    private String answer;

    @Column(name = "description", nullable = false)
    private String description;

    QuizEntity(String question, String answer, String description) {
        this.question = question;
        this.answer = answer;
        this.description = description;
    }

    public static QuizEntity of(String question, String answer, String description) {
       return new QuizEntity(question, answer, description);
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
