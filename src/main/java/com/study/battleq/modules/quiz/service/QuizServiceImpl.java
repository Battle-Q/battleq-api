package com.study.battleq.modules.quiz.service;

import com.study.battleq.modules.quiz.domain.dto.CreateQuizRequest;
import com.study.battleq.modules.quiz.domain.entity.*;
import com.study.battleq.modules.quiz.exception.QuizNotFoundException;
import com.study.battleq.modules.quiz.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;


    @Override
    public QuizDto getQuiz(Long quizId) {
        // 삭제된거 고려
        // 트랜잭션 분리
        QuizEntity quizEntity = quizRepository.findById(quizId).orElseThrow(QuizNotFoundException::thrown);


//        return new QuizDto(quizEntity.getId(), quizEntity.getQuizType(), quizEntity.getQuizData());
        return new QuizDto(quizEntity.getId(), QuizType.SHORT_ANSWER, "");

    }


    @Override
    public void createCatchMind(CreateQuizRequest createQuizRequest) {
//        QuizEntity quiz = QuizEntity.of(createQuizRequest.getQuizType(), createQuizRequest.getQuizData());
        CatchMind quiz = CatchMind.of(createQuizRequest.getQuestion(), createQuizRequest.getAnswer(), createQuizRequest.getDescription(), createQuizRequest.getImage());

        try {
            quizRepository.save(quiz);

        } catch (IllegalArgumentException e) {
            //todo custom Exception 던지기
            throw new IllegalArgumentException();
        }

    }


    @Override
    public void createShortAnswer(CreateQuizRequest createQuizRequest) {
//        QuizEntity quiz = QuizEntity.of(createQuizRequest.getQuizType(), createQuizRequest.getQuizData());
        ShortAnswer quiz = ShortAnswer.of(createQuizRequest.getQuestion(), createQuizRequest.getAnswer(), createQuizRequest.getDescription());

        try {
            quizRepository.save(quiz);

        } catch (IllegalArgumentException e) {
            //todo custom Exception 던지기
            throw new IllegalArgumentException();
        }

    }


    @Override
    public void deleteQuiz(Long quizId) {
        try {
            quizRepository.deleteById(quizId);
        } catch (IllegalArgumentException e) {
            //todo custom Exception 던지기
            throw new IllegalArgumentException();
        }
    }


}
