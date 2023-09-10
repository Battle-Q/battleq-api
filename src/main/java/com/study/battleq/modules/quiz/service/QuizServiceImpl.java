package com.study.battleq.modules.quiz.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.battleq.modules.quiz.domain.dto.CreateQuizItemRequest;
import com.study.battleq.modules.quiz.domain.dto.CreateQuizRequest;
import com.study.battleq.modules.quiz.domain.dto.CreateQuizResponse;
import com.study.battleq.modules.quiz.domain.entity.QuizDto;
import com.study.battleq.modules.quiz.domain.entity.QuizItemDto;
import com.study.battleq.modules.quiz.domain.entity.QuizItem.QuizEntity;
import com.study.battleq.modules.quiz.exception.QuizNotFoundException;
import com.study.battleq.modules.quiz.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;

    @Override
    public QuizDto getQuiz(Long quizId) {
        return null;
    }

    @Override
    public CreateQuizResponse createQuiz(CreateQuizRequest createQuizRequest) {

        QuizEntity from = QuizEntity.from(createQuizRequest);

        try {
            QuizEntity save = quizRepository.save(from);
            return CreateQuizResponse.from(save);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            throw new IllegalArgumentException();
        }

    }

    @Override
    public void createShortAnswer(CreateQuizRequest createQuizRequest) {

    }

    @Override
    public void createCatchMind(CreateQuizRequest createQuizRequest) {

    }

    @Override
    public void createTrueOrFalse(CreateQuizRequest createQuizRequest) {

    }

    @Override
    public void deleteQuiz(Long quizId) {

    }

}

