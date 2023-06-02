package com.study.battleq.modules.quiz.controller.v1;

import com.study.battleq.infrastructure.common.dto.ResponseDto;
import com.study.battleq.modules.quiz.domain.entity.QuizDto;
import com.study.battleq.modules.quiz.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/quizzes")
public class QuizControllerV1 {

    private final QuizService quizService;

    @GetMapping("/{quizId}")
    public ResponseDto<QuizDto> getQuiz(@PathVariable("quizId") String quizId) {

        //ResponseDto<QuizDto>.ok()
        return ResponseDto.ok(quizService.getQuiz(quizId));
    }

}
