package com.study.battleq.modules.board.controller.request;

import com.study.battleq.modules.board.service.dto.BoardDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
public class UpdateBoardRequest {
    @Schema(description = "게시글 내용")
    private String content;
    @Schema(description = "게시글 분류")
    private String category;
    @Schema(description = "중요게시글 여부")
    private boolean priority;

    public BoardDto toDto(){
        return BoardDto.builder()
                .content(content)
                .category(category)
                .priority(priority)
                .build();
    }
}
