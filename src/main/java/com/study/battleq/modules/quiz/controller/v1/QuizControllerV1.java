package com.study.battleq.modules.quiz.controller.v1;

import com.study.battleq.infrastructure.common.dto.ResponseDto;
import com.study.battleq.modules.quiz.domain.dto.CreateQuizRequest;
import com.study.battleq.modules.quiz.domain.dto.CreateQuizResponse;
import com.study.battleq.modules.quiz.service.QuizService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Tag(name = "Quiz-V1")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/quizzes")
public class QuizControllerV1 {

    private final QuizService quizService;

    @Operation(summary = "퀴즈 세트 생성", description = "퀴즈세트를 생성합니다.")
    @PostMapping("")
    public ResponseDto<CreateQuizResponse> createQuiz(
            //@AuthenticationPrincipal BattleQUser user,
            @Valid @RequestBody CreateQuizRequest createQuizRequest) throws Exception {

        CreateQuizResponse quiz = quizService.createQuiz(createQuizRequest);

        return ResponseDto.ok(quiz);
    }
}
