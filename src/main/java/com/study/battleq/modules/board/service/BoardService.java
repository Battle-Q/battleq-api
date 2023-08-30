package com.study.battleq.modules.board.service;

import com.study.battleq.modules.board.domain.entity.BoardEntity;
import com.study.battleq.modules.board.domain.repository.BoardRepository;
import com.study.battleq.modules.board.service.dto.BoardDto;
import com.study.battleq.modules.board.service.dto.response.BoardSearchResponse;
import com.study.battleq.modules.board.service.dto.response.UpdateBoardResponse;
import com.study.battleq.modules.board.service.exception.BoardAuthorizationException;
import com.study.battleq.modules.board.service.exception.BoardNotFoundException;
import com.study.battleq.modules.user.domain.entity.UserEntity;
import com.study.battleq.modules.user.domain.repository.UserQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.PropertyEditorSupport;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    private final UserQueryService userQueryService;

    public Long save(BoardDto boardDto, Long userId) {
        UserEntity userEntity = userQueryService.findById(userId);

        BoardEntity boardEntity = boardRepository.save(BoardEntity.createBoardEntity(boardDto.getTitle(), boardDto.getContent(), boardDto.getCategory(), userEntity));

        return boardEntity.getId();
    }

    public List<BoardSearchResponse> findAll() {
        //TODO : 페이징 & Slice
        List<BoardEntity> boardEntityList = boardRepository.findAllByDeletedAtIsNull();

        return boardEntityList.stream().map(boardEntity -> BoardSearchResponse.of(boardEntity.getId(), boardEntity.getTitle(), boardEntity.getContent(), boardEntity.getCategory(), boardEntity.getUser().getNickname(), boardEntity.getViews(), boardEntity.getLikeCount(), boardEntity.getDislikeCount(), boardEntity.isBest()))
                .collect(Collectors.toList());
    }

    public BoardSearchResponse findById(Long boardId) {
        BoardEntity boardEntity = boardRepository.findByIdAndDeletedAtIsNull(boardId).orElseThrow(BoardNotFoundException::thrown);

        return BoardSearchResponse.of(boardEntity.getId(), boardEntity.getTitle(), boardEntity.getContent(), boardEntity.getCategory(), boardEntity.getUser().getNickname(), boardEntity.getViews(), boardEntity.getLikeCount(), boardEntity.getDislikeCount(), boardEntity.isBest());
    }

    public List<BoardSearchResponse> findByTitle(String title) {
        //TODO : 페이징 & Slice, 데이터가 존재하지 않을시 응답 방식 고려.
        List<BoardEntity> boardEntityList = boardRepository.findByTitleAndDeletedAtIsNull(title);

        return boardEntityList.stream().map(boardEntity -> BoardSearchResponse.of(boardEntity.getId(), boardEntity.getTitle(), boardEntity.getContent(), boardEntity.getCategory(), boardEntity.getUser().getNickname(), boardEntity.getViews(), boardEntity.getLikeCount(), boardEntity.getDislikeCount(), boardEntity.isBest()))
                .collect(Collectors.toList());
    }

    @Transactional
    public UpdateBoardResponse update(Long boardId, BoardDto boardDto, Long userId) {
        BoardEntity boardEntity = boardRepository.findByIdAndDeletedAtIsNull(boardId).orElseThrow(BoardNotFoundException::thrown);

        // 사용자 권한 검증
        if(!verifyBoardUserPermissions(boardEntity.getUser(), userId)){
            throw BoardAuthorizationException.thrown();
        }

        boardEntity.updateBoard(boardDto.getContent(), boardDto.getCategory());

        return UpdateBoardResponse.of(boardEntity.getId(), boardEntity.getTitle(), boardEntity.getContent(), boardEntity.getCategory(), boardEntity.getUser().getNickname(), boardEntity.getViews(), boardEntity.getLikeCount(), boardEntity.getDislikeCount(), boardEntity.isBest());
    }

    @Transactional
    public void delete(Long boardId, Long userId) {
        BoardEntity boardEntity = boardRepository.findByIdAndDeletedAtIsNull(boardId).orElseThrow(BoardNotFoundException::thrown);

        // 사용자 권한 검증
        if(!verifyBoardUserPermissions(boardEntity.getUser(), userId)){
            throw BoardAuthorizationException.thrown();
        }

        boardEntity.delete();
    }

    private boolean verifyBoardUserPermissions(UserEntity userEntity, Long userId) {
        if(!userEntity.getId().equals(userId)){
            return false;
        }
        return true;
    }
}
