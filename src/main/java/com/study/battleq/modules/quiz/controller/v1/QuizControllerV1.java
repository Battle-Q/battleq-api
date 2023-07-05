package com.study.battleq.modules.quiz.controller.v1;

import com.study.battleq.infrastructure.common.dto.ResponseDto;
import com.study.battleq.modules.quiz.domain.dto.CreateQuizRequest;
import com.study.battleq.modules.quiz.domain.entity.QuizDto;
import com.study.battleq.modules.quiz.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public ResponseDto<Void> createQuiz(
            //@AuthenticationPrincipal BattleQUser user,
            @Valid @RequestBody CreateQuizRequest createQuizRequest) {

        switch (createQuizRequest.getQuizType()) {
            case CATCH_MIND -> quizService.createCatchMind(createQuizRequest);
            case SHORT_ANSWER -> quizService.createShortAnswer(createQuizRequest);

        }

        return ResponseDto.ok();
    }


    @DeleteMapping("/{quizId}")
    public ResponseDto<Void> deleteQuiz(@PathVariable("quizId") Long quizId) {
        quizService.deleteQuiz(quizId);
        return ResponseDto.ok();
    }

}
