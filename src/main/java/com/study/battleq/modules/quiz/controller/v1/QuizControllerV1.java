package com.study.battleq.modules.quiz.controller.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.battleq.infrastructure.common.dto.ResponseDto;
import com.study.battleq.modules.quiz.domain.CreateQuizRequest;
import com.study.battleq.modules.quiz.domain.CreateQuizResponse;
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

        return ResponseDto.ok(quizService.getQuiz(quizId));
    }


    @PostMapping("")
    public ResponseDto<CreateQuizResponse> createQuiz(@RequestBody CreateQuizRequest createQuizRequest ) {

        return ResponseDto.ok(quizService.createQuiz(createQuizRequest));
    }

}
