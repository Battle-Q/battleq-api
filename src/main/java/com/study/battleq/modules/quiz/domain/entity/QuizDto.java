package com.study.battleq.modules.quiz.domain.entity;


import com.study.battleq.modules.quiz.domain.entity.QuizItem.Quiz;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class QuizDto {

    private Long id;

    private QuizType quizType;

    private String quizData;

    //todo 전체수정
    public static QuizDto of(){
        return new QuizDto(1L,QuizType.SHORT_ANSWER,"data");
    }

    public static QuizDto entityToDto(Quiz quiz) {
        //todo 파라미터 수정
        return new QuizDto(1L,QuizType.SHORT_ANSWER,"data");
    }

}
