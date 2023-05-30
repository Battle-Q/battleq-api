package com.study.battleq.board.controller;

import com.study.battleq.board.domain.dto.BoardDto;
import com.study.battleq.board.domain.dto.request.CreateBoardRequest;
import com.study.battleq.board.domain.dto.response.BoardSearchResponse;
import com.study.battleq.board.domain.dto.response.CreateBoardResponse;
import com.study.battleq.board.domain.entity.BoardEntity;
import com.study.battleq.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;

    @PostMapping("/api/v1/boards")
    public CreateBoardResponse saveBoard(@RequestBody @Valid CreateBoardRequest request) {
        BoardDto boardDto = request.toDto();

        Long id  = boardService.save(boardDto);

        return new CreateBoardResponse(id);
    }

    @GetMapping("/api/v1/board")
    public BoardSearchResponse findAllBoard(){
        // TODO : ResponseDTO로 변환 필요
        List<BoardEntity> boardEntities = boardService.findAll();
        return new BoardSearchResponse(LocalDateTime.now(), HttpStatus.OK, boardEntities);
    }

    @GetMapping("/api/v1/board/{title}")
    public BoardSearchResponse findByTitle(@PathVariable("title") String title){
        List<BoardEntity> boardEntities = boardService.findByTitle(title);
        return new BoardSearchResponse(LocalDateTime.now(), HttpStatus.OK, boardEntities);
    }
}
