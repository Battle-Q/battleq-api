package com.study.battleq.modules.board.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateBoardDto {
    private String title;
    private String content;
    private String category;
    private boolean priority;
    private String writer;
    //private User user;
}
