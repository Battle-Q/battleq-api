package com.study.battleq.modules.board.service.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@AllArgsConstructor
public class CreateBoardResponse {
    @Schema(description = "게시글 식별자")
    private Long id;
}
