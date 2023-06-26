package com.study.battleq.modules.board.controller;

import com.study.battleq.infrastructure.common.dto.ResponseDto;
import com.study.battleq.modules.board.controller.request.DeleteSearchRequest;
import com.study.battleq.modules.board.controller.request.UpdateBoardRequest;
import com.study.battleq.modules.board.domain.entity.User;
import com.study.battleq.modules.board.domain.repository.UserRepo;
import com.study.battleq.modules.board.service.dto.response.BoardSearchResponse;
import com.study.battleq.modules.board.controller.request.CreateBoardRequest;
import com.study.battleq.modules.board.service.BoardService;
import com.study.battleq.modules.board.domain.entity.BoardEntity;
import com.study.battleq.modules.board.service.dto.response.UpdateBoardResponse;
import com.study.battleq.modules.user.domain.entity.BattleQUser;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;

    private final UserRepo userRepo;
    @PostMapping("/api/v1/boards")
    public ResponseDto<Long> saveBoard(@RequestBody @Valid CreateBoardRequest request, @Parameter(hidden = true) @AuthenticationPrincipal BattleQUser battleQUser) {
        Long id = boardService.save(request.toDto(), battleQUser.getId());
        return ResponseDto.ok(id);
    }
    @GetMapping("/api/test/user/{id}")
    public void userSave(@PathVariable("id") Long id){
        User user = new User();
        user.setEmail("user" + id + "@test.com");
        user.setNickname("user"+ id);
        userRepo.save(user);
    }
    @GetMapping("/api/v1/boards")
    public ResponseDto<List> findAllBoard(){
        return ResponseDto.ok(boardService.findAll());
    }

    @GetMapping("/api/v1/boards/{id}")
    public ResponseDto<BoardSearchResponse> findById(@PathVariable("id") Long boardId){
        BoardSearchResponse boardSearchResponse = boardService.findById(boardId);
        return ResponseDto.ok(boardSearchResponse);
    }

    @GetMapping("/api/v1/boards/title/{title}")
    public ResponseDto<List> findByTitle(@PathVariable("title") String title){
        List<BoardSearchResponse> boardSearchResponseList = boardService.findByTitle(title);
        return ResponseDto.ok(boardSearchResponseList);
    }

    @PutMapping("/api/v1/boards/{id}")
    public ResponseDto<UpdateBoardResponse> updateBoard(@PathVariable("id") Long boardId, @RequestBody @Valid UpdateBoardRequest request) {
        return ResponseDto.ok(boardService.update(boardId, request.toDto()));
    }
    @DeleteMapping("/api/v1/boards/{id}")
    public ResponseDto<Long> deleteBoard(@PathVariable("id") Long boardId) {
        boardService.delete(boardId);
        return ResponseDto.ok();
    }
}
