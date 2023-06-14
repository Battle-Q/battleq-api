package com.study.battleq.modules.board.service;

import com.study.battleq.modules.board.service.dto.response.BoardSearchResponse;
import com.study.battleq.modules.board.service.dto.BoardDto;
import com.study.battleq.modules.board.domain.entity.BoardEntity;
import com.study.battleq.modules.board.domain.repository.BoardRepository;
import com.study.battleq.modules.board.service.exception.NotFoundBoardException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public Long save(BoardDto boardDto){
        BoardEntity boardEntity = boardRepository.save(BoardEntity.of(boardDto.getTitle(), boardDto.getContent(), boardDto.getCategory(), boardDto.isPriority(), boardDto.getWriter()));
        return boardEntity.getId();
    }
    public List<BoardSearchResponse> findAll() {
        List<BoardEntity> boardEntityList = boardRepository.findAllByDeletedAtIsNull();

        return boardEntityList.stream().map(boardEntity -> BoardSearchResponse.of(boardEntity.getTitle(), boardEntity.getContent(), boardEntity.getCategory(), boardEntity.isPriority(), boardEntity.getWriter(), boardEntity.getView()))
                .collect(Collectors.toList());
    }
    public BoardSearchResponse findById(Long boardId) {
        BoardEntity boardEntity = boardRepository.findByIdAndDeletedAtIsNull(boardId)
                .orElseThrow(NotFoundBoardException::thrown);

        return BoardSearchResponse.of(boardEntity.getTitle(), boardEntity.getContent(), boardEntity.getCategory(), boardEntity.isPriority(), boardEntity.getWriter(), boardEntity.getView());
    }

    public List<BoardSearchResponse> findByTitle(String title){
        //TODO : 페이징 & Slice, 데이터가 존재하지 않을시 응답 방식 고려.
        List<BoardEntity> boardEntityList = boardRepository.findByTitleAndDeletedAtIsNull(title);

        return boardEntityList.stream().map(boardEntity -> BoardSearchResponse.of(boardEntity.getTitle(), boardEntity.getContent(), boardEntity.getCategory(), boardEntity.isPriority(), boardEntity.getWriter(), boardEntity.getView()))
                .collect(Collectors.toList());
    }

    public Long update(Long boardId, BoardDto boardDto){
        // TODO : 유저 및 권한검증

        BoardEntity boardEntity = boardRepository.findByIdAndDeletedAtIsNull(boardId)
                .orElseThrow(NotFoundBoardException::thrown);

        boardEntity.updateBoard(boardDto.getContent(), boardDto.getCategory(), boardDto.isPriority());

        return boardEntity.getId();
    }

    public void delete(Long boardId){
        // TODO : 유저 및 권한검증

        BoardEntity boardEntity = boardRepository.findByIdAndDeletedAtIsNull(boardId)
                .orElseThrow(NotFoundBoardException::thrown);

        // board softDelete
        boardEntity.delete();
    }
}
