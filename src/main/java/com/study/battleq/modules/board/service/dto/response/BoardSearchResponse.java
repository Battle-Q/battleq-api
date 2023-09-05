package com.study.battleq.modules.board.service.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.http.HttpStatus;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class BoardSearchResponse {
    @Schema(description = "게시글 id")
    private long id;
    @Schema(description = "게시글 제목")
    private String title;
    @Schema(description = "게시글 좋아요 수")
    private Long likeCount;
    @Schema(description = "게시글 싫어요 수")
    private Long disLikeCount;
    @Schema(description = "게시글 댓글 수")
    private Long commentCount;
    @Schema(description = "게시글 작성자(닉네임)")
    private String writer;
    @Schema(description = "게시글 작성일시(당일 MM/HH전")
    private String timeStamp; // 7초전, 2시간전, 하루전, 3일전, 08.31등)
    @Schema(description = "게시글 최신여부")
    private boolean isNewest;
    @Schema(description = "게시글 급상승 여부")
    private boolean isTrending;

    public static BoardSearchResponse of(Long id, String title, Long likeCount, Long disLikeCount, Long commentCount, String writer, String timeStamp, boolean isNewest, boolean isTrending) {
        return new BoardSearchResponse(id, title, likeCount, disLikeCount, commentCount, writer, timeStamp, isNewest, isTrending);
    }
}
