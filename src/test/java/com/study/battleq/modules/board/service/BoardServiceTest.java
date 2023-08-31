package com.study.battleq.modules.board.service;

import com.study.battleq.modules.board.controller.request.CreateBoardRequest;
import com.study.battleq.modules.board.controller.request.UpdateBoardRequest;
import com.study.battleq.modules.board.domain.entity.BoardEntity;
import com.study.battleq.modules.board.domain.repository.BoardRepository;
import com.study.battleq.modules.board.service.dto.BoardDto;
import com.study.battleq.modules.board.service.dto.response.BoardSearchResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

/**
 * 트랜잭션을 관리하는 것이 주요 책임 (CRUD에 대한 책임은 Repository에 역임)
 * Repository의 동작은 Repository Test에서 확인했으므로 ㄱRepository의 메소드가 제대로 호출되는지만 확인하면 되므로 실제 Repository가 아닌 Mock ㄲ데ㅐ냐새교fmf tkdydgksek.
 * Mockito를 사용하므로 실제 저장/조회가 발생하지 않는다.
 *
 * 비지니스 로직의 플로만 확인한다.
 */
@ExtendWith(MockitoExtension.class)
class BoardServiceTest {
    @InjectMocks
    BoardService boardService;
    @Mock
    BoardRepository boardRepository;

    @Test
    void save_정상() {
        // given
        BoardEntity boardEntity = BoardEntity.of("테스트타이틀", "테스트본문", "자유게시판", false, "user1");
        when(boardRepository.save(any())).thenReturn(boardEntity);

        // when
        BoardDto boardDto = BoardDto.builder()
                .title("title")
                .content("content")
                .category("category")
                .priority(false)
                .writer("user1")
                .build();
        boardService.save(boardDto);

        // then
        verify(boardRepository, times(1)).save(any());
    }

    @Test
    void findAll_정상() {
        //given
        List<BoardEntity> boardEntityList = new ArrayList<>();
        boardEntityList.add(BoardEntity.of("테스트타이틀1", "테스트본문1", "자유게시판", false, "user1"));
        boardEntityList.add(BoardEntity.of("테스트타이틀2", "테스트본문2", "자유게시판", false, "user2"));

        //when
        List<BoardSearchResponse> resultList = boardService.findAll();

        //then
        assertFalse(resultList.isEmpty());
    }

    @Test
    void findById_정상() {
        //given
        BoardEntity boardEntity = BoardEntity.of("테스트타이틀", "테스트본문", "자유게시판", false, "user1");
        given(boardRepository.findByIdAndDeletedAtIsNull(anyLong())).willReturn(Optional.of(boardEntity));
        //when

        BoardSearchResponse result = boardService.findById(1L); // id를 목으로라도 만들어야 되나?

        //then
        verify(boardRepository, times(1)).findByIdAndDeletedAtIsNull(any());
        assertEquals(result.getTitle(), boardEntity.getTitle());
    }

    @Test
    void findByTitle_정상() {
        List<BoardEntity> boardEntityList = new ArrayList<>();
        boardEntityList.add(BoardEntity.of("테스트타이틀1", "테스트본문1", "자유게시판", false, "user1"));
        boardEntityList.add(BoardEntity.of("테스트타이틀2", "테스트본문2", "자유게시판", false, "user2"));

        given(boardRepository.findByTitleAndDeletedAtIsNull(anyString())).willReturn(boardEntityList);

        List<BoardSearchResponse> boardSearchResponseList = boardService.findByTitle("테스트타이틀");
        assertEquals(boardSearchResponseList.size(), 2);
    }

    @Test
    void update_정상() {
        BoardEntity boardEntity = BoardEntity.of("테스트타이틀", "테스트본문", "자유게시판", false, "user1");
        UpdateBoardRequest updateBoardRequest = new UpdateBoardRequest(); // 이건 어떻게 만드냐?....

        given(boardRepository.findByIdAndDeletedAtIsNull(anyLong())).willReturn(Optional.of(boardEntity));

        boardService.update(1L, updateBoardRequest.toDto());

        assertEquals(boardEntity.getContent(), null); // Requestdp 따라 달리져야함.
    }

    @Test
    void delete_정상() {
        BoardEntity boardEntity = BoardEntity.of("테스트타이틀", "테스트본문", "자유게시판", false, "user1");
        given(boardRepository.findByIdAndDeletedAtIsNull(anyLong())).willReturn(Optional.of(boardEntity));

        boardService.delete(1L);

        // 우리꺼 엔티티에 softDelete를 하고 싶은데...
        verify(boardRepository).save(any());
    }
}