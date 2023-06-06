package com.study.battleq.modules.quiz.service;

import com.study.battleq.modules.quiz.domain.entity.QuizDto;
import com.study.battleq.modules.quiz.domain.entity.QuizEntity;
import com.study.battleq.modules.quiz.domain.entity.QuizType;
import com.study.battleq.modules.quiz.repository.QuizRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuizServiceTest {

    @InjectMocks
    QuizServiceImpl quizService;

    @Mock
    QuizRepository quizRepository;


    @Test
    @DisplayName("퀴즈 정보가 정상 조회된다")
    void 퀴즈_조회() {

        when(quizRepository.findById(1L)).thenReturn(Optional.of(QuizEntity.of(QuizType.SHORT_ANSWER, "short")));

        QuizDto quiz = quizService.getQuiz(1L);

        assertEquals("SHORT_ANSWER", quiz.getQuizType().toString());

    }

    @Test
    @DisplayName("없는 퀴즈 정보가 조회에 실패한다 Exception 발생")
    void 없는_퀴즈_조회() {

        when(quizRepository.findById(0L)).thenThrow(NoSuchElementException.class);

        assertThrows(NoSuchElementException.class, () -> quizService.getQuiz(0L));

    }
}