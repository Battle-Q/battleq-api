package com.study.battleq.modules.board.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DeleteSearchRequest {
    @Schema(description = "게시글 식별자(ID)")
    private Long boardId;
}
