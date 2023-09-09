package com.study.battleq.modules.quiz.service;

import com.study.battleq.modules.quiz.domain.dto.CreateQuizItemRequest;
import com.study.battleq.modules.quiz.domain.entity.QuizItemDto;

public interface QuizItemService {
    QuizItemDto getQuiz(Long quizId);


    void createQuiz(CreateQuizItemRequest createQuizRequest);
    void createShortAnswer(CreateQuizItemRequest createQuizRequest);
    void createCatchMind(CreateQuizItemRequest createQuizRequest);
    void createTrueOrFalse(CreateQuizItemRequest createQuizRequest);

    void deleteQuiz(Long quizId);
}
