package com.study.battleq.modules.quiz.service;

import com.study.battleq.modules.quiz.domain.entity.QuizDto;
import com.study.battleq.modules.quiz.domain.entity.QuizEntity;
import com.study.battleq.modules.quiz.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService{

    private final QuizRepository quizRepository;


    @Override
    public QuizDto getQuiz(String quizId) {
        Optional<QuizEntity> quizEntity = quizRepository.findById(quizId);

        return new QuizDto(quizEntity.get().getId(), quizEntity.get().getQuizType(), quizEntity.get().getQuizData());

    }

}
