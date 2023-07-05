package com.study.battleq.modules.quiz.service;

import com.study.battleq.modules.quiz.domain.dto.CreateQuizRequest;
import com.study.battleq.modules.quiz.domain.entity.QuizDto;

public interface QuizService {
    QuizDto getQuiz(Long quizId);

    void createCatchMind(CreateQuizRequest createQuizRequest);
    void createShortAnswer(CreateQuizRequest createQuizRequest);

    void deleteQuiz(Long quizId);
}
