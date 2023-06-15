package com.study.battleq.modules.board.service.dto;

import com.study.battleq.modules.board.domain.entity.BoardEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {
    private Long boardId;
    private String title;
    private String content;
    private String category;
    private boolean priority;
    private String writer;
    private int view;
}
