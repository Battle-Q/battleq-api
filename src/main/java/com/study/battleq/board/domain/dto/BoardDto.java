package com.study.battleq.board.domain.dto;

import com.study.battleq.board.domain.entity.BoardEntity;
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
    private boolean importance;
    private String writer;
    private int view;

    public BoardDto(BoardEntity boardEntity){
        this.boardId = boardEntity.getId();
        this.title = boardEntity.getTitle();
        this.content = boardEntity.getContent();
        this.category = boardEntity.getTitle();
        this.importance = boardEntity.isImportance();
        this.writer = boardEntity.getWriter();
        this.view = boardEntity.getView();
    }

    //TODO : toEntity()
}
