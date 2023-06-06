package com.study.battleq.modules.quiz.controller.v1;

import com.study.battleq.infrastructure.common.dto.ResponseDto;
import com.study.battleq.modules.quiz.domain.entity.QuizDto;
import com.study.battleq.modules.quiz.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/quizzes")
public class QuizControllerV1 {

    private final QuizService quizService;

    @GetMapping("/{quizId}")
    public ResponseDto<QuizDto> getQuiz(@PathVariable("quizId") Long quizId) {

        //ResponseDto<QuizDto>.ok()
        return ResponseDto.ok(quizService.getQuiz(quizId));
    }

}
