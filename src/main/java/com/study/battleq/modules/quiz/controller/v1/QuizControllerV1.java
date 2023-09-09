package com.study.battleq.modules.quiz.controller.v1;

import com.study.battleq.infrastructure.common.dto.ResponseDto;
import com.study.battleq.modules.quiz.domain.dto.CreateQuizRequest;
import com.study.battleq.modules.quiz.domain.entity.QuizDto;
import com.study.battleq.modules.quiz.domain.entity.QuizType;
import com.study.battleq.modules.quiz.exception.NoneQuizTypeException;
import com.study.battleq.modules.quiz.exception.WrongQuizTypeException;
import com.study.battleq.modules.quiz.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
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
            @Valid @RequestBody CreateQuizRequest createQuizRequest) throws Exception {


        //todo DTO에 validation 추가하기
        if(StringUtils.isEmpty(createQuizRequest.getQuizType().toString())){
            throw NoneQuizTypeException.thrown();
        }

        if(EnumUtils.isValidEnum(QuizType.class, createQuizRequest.getQuizType().toString())){
            throw WrongQuizTypeException.thrown();
        }

        quizService.createQuiz(createQuizRequest);

        return ResponseDto.ok();
    }


    @DeleteMapping("/{quizId}")
    public ResponseDto<Void> deleteQuiz(@PathVariable("quizId") Long quizId) {
        quizService.deleteQuiz(quizId);
        return ResponseDto.ok();
    }

}
