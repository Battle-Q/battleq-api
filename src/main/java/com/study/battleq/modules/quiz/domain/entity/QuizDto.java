package com.study.battleq.modules.quiz.domain.entity;


import com.study.battleq.modules.quiz.domain.entity.QuizItem.QuizEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class QuizDto {

    private Long id;

    private QuizItemType quizType;

    private String quizData;

    //todo 전체수정
    public static QuizDto of(){
        return new QuizDto(1L, QuizItemType.SHORT_ANSWER,"data");
    }

    public static QuizDto from(QuizEntity quiz) {
        //todo 파라미터 수정
        return new QuizDto(1L, QuizItemType.SHORT_ANSWER,"data");
    }

}
