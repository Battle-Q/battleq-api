package com.study.battleq.modules.quiz.controller.v1;

import com.study.battleq.infrastructure.common.dto.ResponseDto;
import com.study.battleq.modules.quiz.domain.dto.CreateQuizItemRequest;
import com.study.battleq.modules.quiz.domain.entity.QuizItemDto;
import com.study.battleq.modules.quiz.domain.entity.QuizItemType;
import com.study.battleq.modules.quiz.exception.NoneQuizTypeException;
import com.study.battleq.modules.quiz.exception.WrongQuizTypeException;
import com.study.battleq.modules.quiz.service.QuizItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Tag(name = "Quiz-Item-V1")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/quiz-items")
public class QuizItemControllerV1 {

    private final QuizItemService quizItemService;


//    @Operation(summary = "전체 퀴즈 목록 조회", description = "전체 퀴즈 목록을 조회합니다.")
//    @GetMapping("")
//    public ResponseDto<List<QuizItemDto>> getQuizList() {
//        //todo 미구현
//        return ResponseDto.ok(new ArrayList<>());
//    }

    @Operation(summary = "퀴즈아이템 상세 조회", description = "퀴즈 아이템 상세정보를 조회합니다.")
    @GetMapping("/{quizId}")
    public ResponseDto<QuizItemDto> getQuiz(@PathVariable("quizId") Long quizId) {
        return ResponseDto.ok(quizItemService.getQuiz(quizId));
    }


    @Operation(summary = "퀴즈 아이템 생성", description = "퀴즈 아이템을 생성합니다.")
    @PostMapping("")
    public ResponseDto<Void> createQuiz(
            //@AuthenticationPrincipal BattleQUser user,
            @Valid @RequestBody CreateQuizItemRequest createQuizRequest) throws Exception {


        //todo DTO에 validation 추가하기
        if(StringUtils.isEmpty(createQuizRequest.getQuizType().toString())){
            throw NoneQuizTypeException.thrown();
        }

        if(EnumUtils.isValidEnum(QuizItemType.class, createQuizRequest.getQuizType().toString())){
            throw WrongQuizTypeException.thrown();
        }

        quizItemService.createQuiz(createQuizRequest);

        return ResponseDto.ok();
    }

    @Operation(summary = "퀴즈 아이템 삭제", description = "퀴즈 아이템 ID로 퀴즈 아이템을 삭제합니다.")
    @DeleteMapping("/{quizId}")
    public ResponseDto<Void> deleteQuiz(@PathVariable("quizId") Long quizId) {
        quizItemService.deleteQuiz(quizId);
        return ResponseDto.ok();
    }

}
