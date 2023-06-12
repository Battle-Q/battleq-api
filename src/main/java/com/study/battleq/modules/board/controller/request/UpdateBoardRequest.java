package com.study.battleq.modules.board.controller.request;

import com.study.battleq.modules.board.service.dto.BoardDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
public class UpdateBoardRequest {
    @NotEmpty(message = "제목은 필수값 입니다.")
    private String title;
    private String content;
    private String category;
    private boolean priority;

    //private User user;

    public BoardDto toDto(){
        return BoardDto.builder()
                .title(title)
                .content(content)
                .category(category)
                .priority(priority)
                //writer(User)
                .view(0)
                .build();
    }
}
