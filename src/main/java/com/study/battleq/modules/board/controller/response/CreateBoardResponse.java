package com.study.battleq.modules.board.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@AllArgsConstructor
public class CreateBoardResponse {
    private Long id;
}
