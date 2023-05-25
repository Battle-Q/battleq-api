package com.study.battleq.board.service;

import com.study.battleq.board.domain.dto.BoardDto;
import com.study.battleq.board.domain.entity.BoardEntity;
import com.study.battleq.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public Long save(BoardDto boardDto){
        //임시 : toEntity()와 같은 DTO > Entity 편의 메서드 사용해도 되나?
        BoardEntity boardEntity = BoardEntity.builder()
                .title(boardDto.getTitle())
                .content(boardDto.getContent())
                .category(boardDto.getCategory())
                .importance(boardDto.isImportance())
                .writer(boardDto.getWriter())
                .build();

        boardEntity = boardRepository.save(boardEntity);
        return boardEntity.getId();
    }

    public BoardEntity findById(Long id) {
        Optional<BoardEntity> boardEntity = boardRepository.findById(id);

        /* 어떤게 좋은 코드인가?
        BoardEntity board = boardRepository.findById(id).orElseThrow(() -> new Exception());

        if(!boardEntity.isPresent()){
            new throws Exception();
        }
        */

        return boardEntity.get();
    }

    // BoardDto로 받아야되나?
    public List<BoardEntity> findByTitle(String title){
        //TODO : 페이징 및 JPA Find 메서드 개선 필요
        List<BoardEntity> boardEntityList = boardRepository.findByTitle(title);

        return boardEntityList;
    }

    public List<BoardEntity> findAll(){
        return boardRepository.findAll();
    }
}
