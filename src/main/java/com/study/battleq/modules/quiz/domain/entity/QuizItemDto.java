package com.study.battleq.modules.quiz.domain.entity;


import com.study.battleq.modules.quiz.domain.entity.QuizItem.QuizItemEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class QuizItemDto {

    private Long id;

    private QuizItemType quizType;

    private String quizData;

    //todo 전체수정
    public static QuizItemDto of(){
        return new QuizItemDto(1L, QuizItemType.SHORT_ANSWER,"data");
    }

    public static QuizItemDto entityToDto(QuizItemEntity quiz) {
        //todo 파라미터 수정
        return new QuizItemDto(1L, QuizItemType.SHORT_ANSWER,"data");
    }

}
