package com.study.battleq.modules.quiz.service;

import com.study.battleq.modules.quiz.domain.entity.QuizDto;

public interface QuizService {
    QuizDto getQuiz(String quizId);
}
