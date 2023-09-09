package com.study.battleq.modules.quiz.service;

import com.study.battleq.modules.quiz.domain.dto.CreateQuizItemRequest;
import com.study.battleq.modules.quiz.domain.dto.CreateQuizRequest;
import com.study.battleq.modules.quiz.domain.entity.QuizDto;
import com.study.battleq.modules.quiz.domain.entity.QuizItemDto;

public interface QuizService {
    QuizDto getQuiz(Long quizId);


    void createQuiz(CreateQuizRequest createQuizRequest);
    void createShortAnswer(CreateQuizRequest createQuizRequest);
    void createCatchMind(CreateQuizRequest createQuizRequest);
    void createTrueOrFalse(CreateQuizRequest createQuizRequest);

    void deleteQuiz(Long quizId);
}
