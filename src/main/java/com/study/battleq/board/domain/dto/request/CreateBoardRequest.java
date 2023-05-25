package com.study.battleq.board.domain.dto.request;

import com.study.battleq.board.domain.dto.BoardDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateBoardRequest {
    @NotEmpty
    private String title;
    private String content;
    private String category;
    private boolean importance;

    //private User user;

    public BoardDto toDto(){
        return BoardDto.builder()
                .title(title)
                .content(content)
                .category(category)
                .importance(importance)
                //writer(User)
                .view(0)
                .build();
    }
}
