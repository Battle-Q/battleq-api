package com.study.battleq.modules.board.controller.request;

import com.study.battleq.modules.board.service.dto.BoardDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
public class UpdateBoardRequest {
    private String content;
    private String category;
    private boolean priority;

    public BoardDto toDto(){
        return BoardDto.builder()
                .content(content)
                .category(category)
                .priority(priority)
                .build();
    }
}
