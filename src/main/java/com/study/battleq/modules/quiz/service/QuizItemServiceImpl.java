package com.study.battleq.modules.quiz.service;

import com.study.battleq.modules.quiz.domain.dto.CreateQuizItemRequest;
import com.study.battleq.modules.quiz.domain.entity.*;
import com.study.battleq.modules.quiz.domain.entity.QuizItem.QuizItemEntity;
import com.study.battleq.modules.quiz.exception.QuizNotFoundException;
import com.study.battleq.modules.quiz.repository.QuizItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class QuizItemServiceImpl implements QuizItemService {

    private final QuizItemRepository quizItemRepository;
    @Override
    public void createQuiz(CreateQuizItemRequest createQuizRequest) {
//        QuizEntity quiz = QuizEntity.of(createQuizRequest.getQuizType(), createQuizRequest.getQuizData());
        switch (createQuizRequest.getQuizType()) {
            case SHORT_ANSWER -> createShortAnswer(createQuizRequest);
//            case TRUE_OR_FALSE -> createShortAnswer(createQuizRequest);
//            case MULTIPLE_CHOICE -> createShortAnswer(createQuizRequest);
//            case INITIAL -> createShortAnswer(createQuizRequest);
            case CATCH_MIND -> createCatchMind(createQuizRequest);
//            case MAKE_ORDER -> quizService.createCatchMind(createQuizRequest);
        }

        
    }

    @Override
    public QuizItemDto getQuiz(Long quizId) {
        // 삭제된거 고려
        // 트랜잭션 분리
        QuizItemEntity quiz = quizItemRepository.findByIdAndDeletedAtIsNull(quizId).orElseThrow(QuizNotFoundException::thrown);

//        return new QuizDto(quizEntity.getId(), quizEntity.getQuizType(), quizEntity.getQuizData());
        return QuizItemDto.entityToDto(quiz);

    }

    @Override
    public void createShortAnswer(CreateQuizItemRequest createQuizRequest) {
////        QuizEntity quiz = QuizEntity.of(createQuizRequest.getQuizType(), createQuizRequest.getQuizData());
//        ShortAnswer quiz = ShortAnswer.of(createQuizRequest.getQuestion(), createQuizRequest.getAnswer(), createQuizRequest.getDescription());
//
//        try {
//            quizRepository.save(quiz);
//
//        } catch (IllegalArgumentException e) {
//            log.error("fail createShortAnswer : {}",e.getMessage());
//            throw new IllegalArgumentException();
//        }
    }

    @Override
    public void createTrueOrFalse(CreateQuizItemRequest createQuizRequest) {
////        QuizEntity quiz = QuizEntity.of(createQuizRequest.getQuizType(), createQuizRequest.getQuizData());
//        ShortAnswer quiz = ShortAnswer.of(createQuizRequest.getQuestion(), createQuizRequest.getAnswer(), createQuizRequest.getDescription());
//
//        try {
//            quizRepository.save(quiz);
//
//        } catch (IllegalArgumentException e) {
//            log.error("fail createTrueOrFalse : {}",e.getMessage());
//            throw new IllegalArgumentException();
//        }

    }

    @Override
    public void createCatchMind(CreateQuizItemRequest createQuizRequest) {
//        QuizEntity quiz = QuizEntity.of(createQuizRequest.getQuizType(), createQuizRequest.getQuizData());
//        CatchMind quiz = CatchMind.of(createQuizRequest.getQuestion(), createQuizRequest.getAnswer(), createQuizRequest.getDescription(), createQuizRequest.getImage());
//
//        try {
//            quizRepository.save(quiz);
//
//        } catch (IllegalArgumentException e) {
//            log.error("fail createCatchMind : {}",e.getMessage());
//            throw new IllegalArgumentException();
//        }

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
