package com.study.battleq.modules.quiz.controller.v1;

import com.study.battleq.infrastructure.common.dto.ResponseDto;
import com.study.battleq.modules.quiz.domain.dto.CreateQuizRequest;
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
    @Operation(summary = "퀴즈 세트 생성", description = "퀴즈세트를 생성합니다.")
    @PostMapping("")
    public ResponseDto<Void> createQuizSet(
            //@AuthenticationPrincipal BattleQUser user,
            @Valid @RequestBody CreateQuizRequest createQuizRequest) throws Exception {
        //todo DTO에 validation 추가하기
        return ResponseDto.ok();
    }
}
