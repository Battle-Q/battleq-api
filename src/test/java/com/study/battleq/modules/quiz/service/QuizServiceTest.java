package com.study.battleq.modules.quiz.service;

import com.study.battleq.modules.quiz.domain.dto.CreateQuizRequest;
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
import org.mockito.stubbing.OngoingStubbing;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class QuizServiceTest {

    @InjectMocks
    QuizServiceImpl quizService;

    @Mock
    QuizRepository quizRepository;

    @Test
    @DisplayName("퀴즈를 정상 생성한다.")
    void 퀴즈_생성() {

        QuizEntity save = QuizEntity.of(QuizType.SHORT_ANSWER, "quiz data");

        when(quizRepository.save(any())).thenReturn(save);

        CreateQuizRequest createQuizRequest = CreateQuizRequest.of(QuizType.SHORT_ANSWER, "quiz data");
        quizService.createQuiz(createQuizRequest);

        assertEquals(QuizType.SHORT_ANSWER, save.getQuizType());
        assertEquals("quiz data", save.getQuizData());

    }


    @Test
    @DisplayName("퀴즈 정보가 정상 조회된다")
    void 퀴즈_조회() {

        when(quizRepository.findById(anyLong())).thenReturn(Optional.of(QuizEntity.of(QuizType.SHORT_ANSWER, "short")));

        QuizDto quiz = quizService.getQuiz(1L);

        assertEquals("SHORT_ANSWER", quiz.getQuizType().toString());

    }

    @Test
    @DisplayName("없는 퀴즈 정보가 조회에 실패한다 Exception 발생")
    void 없는_퀴즈_조회() {

        when(quizRepository.findById(0L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> quizService.getQuiz(0L));

    }

    @Test
    @DisplayName("퀴즈를 정상적으로 삭제한다")
    void 퀴즈_삭제() {

        //삭제된 객체를 어떻게 확인하지?
        //void 테스트는 어떻게하나?
//         service 단에서 repository mock 객체를 사용했을 때 삭제 테스트는 없는거 같다
        quizService.deleteQuiz(anyLong());

        verify(quizRepository, times(1)).deleteById(anyLong());

    }


    @Test
    @DisplayName("없는 퀴즈ID로 퀴즈를 삭제할때는 에러를 반환한다")
    void 없는_퀴즈_삭제() {

        //어떻게 테스트를 해야하나

//        when(quizService.deleteQuiz(anyLong())).

//        assertThrows(IllegalArgumentException.class, () -> quizService.deleteQuiz(0L));

    }
}