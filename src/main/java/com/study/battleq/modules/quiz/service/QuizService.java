package com.study.battleq.modules.quiz.service;

import com.study.battleq.modules.quiz.domain.CreateQuizRequest;
import com.study.battleq.modules.quiz.domain.CreateQuizResponse;
import com.study.battleq.modules.quiz.domain.entity.QuizDto;

public interface QuizService {
    QuizDto getQuiz(Long quizId);

    CreateQuizResponse createQuiz(CreateQuizRequest createQuizRequest);
}
