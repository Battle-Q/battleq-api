package com.study.battleq.modules.board.controller.request;

import com.study.battleq.modules.board.service.dto.BoardDto;
import io.netty.channel.ChannelHandler;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
public class CreateBoardRequest {
    @NotEmpty(message = "제목은 필수값 입니다.")
    @Schema(description = "게시글 제목", nullable = false)
    private String title;
    @Schema(description = "게시글 내용")
    private String content;
    @Schema(description = "게시글 분류")
    private String category;
    @Schema(description = "중요 게시글 여부")
    private boolean priority;

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
