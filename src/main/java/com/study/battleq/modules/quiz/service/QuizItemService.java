package com.study.battleq.modules.quiz.service;

import com.study.battleq.modules.quiz.domain.dto.CreateQuizItemRequest;
import com.study.battleq.modules.quiz.domain.entity.QuizItemDto;

public interface QuizItemService {
    QuizItemDto getQuizItem(Long quizId);


    void createQuizItem(CreateQuizItemRequest createQuizItemRequest);
    void createShortAnswer(CreateQuizItemRequest createQuizItemRequest);
    void createCatchMind(CreateQuizItemRequest createQuizItemRequest);
    void createTrueOrFalse(CreateQuizItemRequest createQuizItemRequest);

    void deleteQuizItem(Long quizId);
}
