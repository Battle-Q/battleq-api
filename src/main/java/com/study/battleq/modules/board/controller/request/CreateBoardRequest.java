package com.study.battleq.modules.board.controller.request;

import com.study.battleq.modules.board.service.dto.BoardDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
public class CreateBoardRequest {
    @NotEmpty(message = "제목은 필수값 입니다.")
    private String title;
    private String content;
    private String category;
    private boolean priority;
    // TODO : remove
    private Long id;
    public BoardDto toDto(){
        return BoardDto.builder()
                .title(title)
                .content(content)
                .category(category)
                .priority(priority)
                .view(0)
                .build();
    }
}
