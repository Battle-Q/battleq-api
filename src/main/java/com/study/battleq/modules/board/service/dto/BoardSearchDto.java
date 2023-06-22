package com.study.battleq.modules.board.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardSearchDto {
    private String title;
    private String content;
    private String category;
    private String writer;
}
