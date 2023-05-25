package com.study.battleq.board.controller;

import com.study.battleq.board.domain.dto.BoardDto;
import com.study.battleq.board.domain.dto.request.CreateBoardRequest;
import com.study.battleq.board.domain.dto.response.BoardSearchResponse;
import com.study.battleq.board.domain.dto.response.CreateBoardResponse;
import com.study.battleq.board.domain.entity.BoardEntity;
import com.study.battleq.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("api/v1/board")
    public BoardSearchResponse findAllBoard(){
        // Entity 그대로 나가도 되나?
        List<BoardEntity> boardEntityList = boardService.findAll();
        return new BoardSearchResponse(LocalDateTime.now(), HttpStatus.OK, boardEntityList);
    }

    @GetMapping("api/v1/board/{title}")
    public BoardSearchResponse findByTitle(@PathVariable("title") String title){
        List<BoardEntity> boardEntityList = boardService.findByTitle(title);
        return new BoardSearchResponse(LocalDateTime.now(), HttpStatus.OK, boardEntityList);
    }
}
