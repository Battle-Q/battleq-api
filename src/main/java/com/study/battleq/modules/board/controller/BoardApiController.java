package com.study.battleq.modules.board.controller;

import com.study.battleq.infrastructure.common.dto.ResponseDto;
import com.study.battleq.modules.board.controller.request.CreateBoardRequest;
import com.study.battleq.modules.board.controller.request.UpdateBoardRequest;
import com.study.battleq.modules.board.service.BoardService;
import com.study.battleq.modules.board.service.dto.response.BoardSearchResponse;
import com.study.battleq.modules.board.service.dto.response.UpdateBoardResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "boards", description = "게시글 API")
@RestController
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;

    @Operation(summary = "게시글 저장", description = "게시글 제목, 내용, 분류, 중요도 여부를 이용하여 게시글을 신규로 저장합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "게시글 저장 성공", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근(유저권한)", content = @Content(schema = @Schema(implementation = Exception.class)))
    })
    @PostMapping("/api/v1/boards")
    public ResponseDto<Long> saveBoard(@RequestBody @Valid CreateBoardRequest createBoardRequest) {
        Long id = boardService.save(createBoardRequest.toDto());
        return ResponseDto.ok(id);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "게시글 조회 성공", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근(페이징)", content = @Content(schema = @Schema(implementation = Exception.class)))
    })
    @Operation(summary = "게시글 전체 조회", description = "게시글을 최근 10건 단위로 조회합니다. ")
    @GetMapping("/api/v1/boards")
    public ResponseDto<List> findAllBoard(@Parameter(name = "page", description = "페이지") @RequestParam Integer page) {
        return ResponseDto.ok(boardService.findAll());
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "게시글 조회 성공", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근(게시글 식별자)", content = @Content(schema = @Schema(implementation = Exception.class)))
    })
    @Operation(summary = "게시글 id로 단건 조회", description = "게시글을 id로 단건 조회합니다.")
    @GetMapping("/api/v1/boards/{id}")
    public ResponseDto<BoardSearchResponse> findById(@Parameter(name = "id", description = "게시글의 식별자(ID)", in = ParameterIn.PATH) @PathVariable("id") Long boardId) {
        BoardSearchResponse boardSearchResponse = boardService.findById(boardId);
        return ResponseDto.ok(boardSearchResponse);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "게시글 조회 성공", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근(페이징)", content = @Content(schema = @Schema(implementation = Exception.class)))
    })
    @Operation(summary = "게시글 제목으로 조회", description = "게시글을 제목으로 다건 조회합니다.")
    @GetMapping("/api/v1/boards/title/{title}")
    public ResponseDto<List> findByTitle(@Parameter(name = "title", description = "게시글 제목", in = ParameterIn.PATH) @PathVariable("title") String title, @Parameter(name = "page", description = "페이지") @RequestParam Integer page) {
        List<BoardSearchResponse> boardSearchResponseList = boardService.findByTitle(title);
        return ResponseDto.ok(boardSearchResponseList);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "게시글 수정 성공", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근(게시글 식별자, 유저 권한등)", content = @Content(schema = @Schema(implementation = Exception.class)))
    })
    @Operation(summary = "게시글 업데이트", description = "id에 해당하는 게시글을 수정합니다.")
    @PutMapping("/api/v1/boards/{id}")
    public ResponseDto<UpdateBoardResponse> updateBoard(@Parameter(name = "id", description = "게시글 식별자", in = ParameterIn.PATH) @PathVariable("id") Long boardId, @RequestBody @Valid UpdateBoardRequest request) {
        return ResponseDto.ok(boardService.update(boardId, request.toDto()));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "게시글 삭제 성공", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근(게시글 식별자, 유저 권한등)", content = @Content(schema = @Schema(implementation = Exception.class)))
    })
    @Operation(summary = "게시글을 삭제", description = "id에 해당하는 게시글을 삭제합니다.")
    @DeleteMapping("/api/v1/boards/{id}")
    public ResponseDto<Long> deleteBoard(@Parameter(name = "id", description = "게시글 식별자", in = ParameterIn.PATH) @PathVariable("id") Long boardId) {
        boardService.delete(boardId);
        return ResponseDto.ok();
    }
}
