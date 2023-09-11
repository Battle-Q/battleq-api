package com.study.battleq.modules.quiz.domain.entity.QuizItem;

import com.study.battleq.infrastructure.common.entity.BaseEntity;
import com.study.battleq.modules.quiz.domain.dto.CreateQuizItemRequest;
import com.study.battleq.modules.quiz.domain.entity.QuizItemType;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "quizzes_item")
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "QUIZ_TYPE")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuizItemEntity extends BaseEntity {

    @Column(name = "quiz_id", nullable = false)
    private Long quizId;

    @Column(name = "quiz_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private QuizItemType quizType;

    @Column(name = "quiz_name", nullable = false)
    private String quizName;

    @Column(name = "question", nullable = false)
    private String question;

    @Column(name = "answer", nullable = false)
    private String answer;

    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "image", nullable = true)
    private String image;

    // todo 항목으로 user의 어떤 속성을 매핑할지? (id, email)
    // todo createaBy 임시로 true로 했음 생성자 채우는 로직 구현하기
    @Column(name = "created_by", nullable = true)
    private String createdBy;

    public QuizItemEntity(Long quizId, QuizItemType quizType, String quizName, String question, String answer, String description, String image, String createdBy) {
        this.quizId = quizId;
        this.quizType = quizType;
        this.quizName = quizName;
        this.question = question;
        this.answer = answer;
        this.description = description;
        this.image = image;
        this.createdBy = createdBy;
    }

    public static QuizItemEntity of(Long quizId, QuizItemType quizType, String quizName, String question, String answer, String description, String image, String createdBy) {
        return new QuizItemEntity(quizId, quizType, quizName, question, answer, description, image, createdBy);
    }

    public static QuizItemEntity ofByBuilder(CreateQuizItemRequest createQuizItemRequest) {
        return QuizItemEntity.builder()
                .quizId(createQuizItemRequest.getQuizId())
                .quizType(createQuizItemRequest.getQuizType())
                .quizName(createQuizItemRequest.getQuizName())
                .question(createQuizItemRequest.getQuestion())
                .answer(createQuizItemRequest.getAnswer())
                .description(createQuizItemRequest.getDescription())
                .image(createQuizItemRequest.getImage())
                .build();
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
