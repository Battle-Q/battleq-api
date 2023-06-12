package com.study.battleq.modules.board.controller;

import com.study.battleq.infrastructure.common.dto.ResponseDto;
import com.study.battleq.modules.board.controller.request.DeleteSearchRequest;
import com.study.battleq.modules.board.controller.request.UpdateBoardRequest;
import com.study.battleq.modules.board.service.dto.BoardDto;
import com.study.battleq.modules.board.controller.request.CreateBoardRequest;
import com.study.battleq.modules.board.service.BoardService;
import com.study.battleq.modules.board.domain.entity.BoardEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;

    @PostMapping("/api/v1/boards")
    public ResponseDto<Long> saveBoard(@RequestBody @Valid CreateBoardRequest request) {
        BoardDto boardDto = request.toDto();
        Long id = boardService.save(boardDto);

        return ResponseDto.ok(id);
    }

    @GetMapping("/api/v1/boards")
    public ResponseDto<List> findAllBoard(){
        // TODO 선후처리?
        List<BoardEntity> boardEntities = boardService.findAll();
        return ResponseDto.ok(boardEntities);
    }

    @GetMapping("/api/v1/boards/{title}")
    public ResponseDto<List> findByTitle(@PathVariable("title") String title){
        List<BoardEntity> boardEntities = boardService.findByTitle(title);

        return ResponseDto.ok(boardEntities);
    }

    //TODO 수정, 삭제(softDelete)
    @PostMapping("/api/v1/boards/{id}")
    public ResponseDto<Long> updateBoard(@PathVariable("id") Long boardId, @RequestBody @Valid UpdateBoardRequest request) {

        return ResponseDto.ok();
    }
    @PostMapping("/api/v1/boards/{id}")
    public ResponseDto<Long> deleteBoard(@PathVariable("id") Long boardId, @RequestBody @Valid DeleteSearchRequest request) {

        return ResponseDto.ok();
    }
}
