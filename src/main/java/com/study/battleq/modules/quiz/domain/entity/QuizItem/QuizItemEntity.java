package com.study.battleq.modules.quiz.domain.entity.QuizItem;

import com.study.battleq.infrastructure.common.entity.BaseEntity;
import com.study.battleq.modules.quiz.domain.entity.QuizItemType;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "quizzes_item")
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "QUIZ_TYPE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuizItemEntity extends BaseEntity {

    @Column(name = "quiz_type", nullable = false)
    private QuizItemType quizType;

    @Column(name = "quiz_name", nullable = false)
    private String quizName;

    @Column(name = "question", nullable = false)
    private String question;

    @Column(name = "answer", nullable = false)
    private String answer;

    @Column(name = "description", nullable = false)
    private String description;

    //todo 항목으로 user의 어떤 속성을 매핑할지? (id, email)
    @Column(name = "created_by", nullable = false)
    private String createdBy;

    public QuizItemEntity(QuizItemType quizType, String quizName, String question, String answer, String description, String createdBy) {
        this.quizType = quizType;
        this.quizName = quizName;
        this.question = question;
        this.answer = answer;
        this.description = description;
        this.createdBy = createdBy;
    }

    public static QuizItemEntity of(QuizItemType quizType, String quizName, String question, String answer, String description, String createdBy) {
        return new QuizItemEntity(quizType, quizName, question, answer, description, createdBy);
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
