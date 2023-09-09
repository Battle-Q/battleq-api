package com.study.battleq.modules.quiz.domain.dto;

import com.study.battleq.modules.quiz.domain.entity.QuizCategoryType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@NoArgsConstructor
public class CreateQuizRequest {
    @NotEmpty(message = "title 은 필수입니다.")
    private String title;

    @NotEmpty(message = "description 은 필수입니다.")
    private String description;

//    @NotEmpty(message = "category 를 선택해주세요.")
    private QuizCategoryType category;

    private List<CreateQuizItemRequest> quizItemRequestList;
}
