package com.study.battleq.modules.board.domain.repository;

import com.study.battleq.modules.board.domain.entity.BoardEntity;
import com.study.battleq.modules.board.service.BoardService;
import com.study.battleq.modules.board.service.exception.NotFoundBoardException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("local")
class BoardRepositoryTest {
    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardService boardService;

    @PersistenceContext
    EntityManager entityManager;

    @AfterEach
    void 게시판_초기화(){
        boardRepository.deleteAll();
    }

    @Test
    void 게시판_Id_정상_조회() {
        //given
        BoardEntity saveBoard = boardRepository.save(BoardEntity.of("제목", "본문", "자유게시판", false, "user1"));

        //when
        Optional<BoardEntity> validBoard = boardRepository.findByIdAndDeletedAtIsNull(saveBoard.getId());

        //then
        assertEquals(saveBoard.getId(), validBoard.get().getId());
    }
    @Test
    void 계시판_Id_비정상_조회(){
        //given
        BoardEntity saveBoard = boardRepository.save(BoardEntity.of("비정상", "본문", "자유게시판", false, "user1"));

        //when
        Long wrongId = saveBoard.getId() + 999;
        Optional<BoardEntity> validBoard = boardRepository.findByIdAndDeletedAtIsNull(wrongId);

        //then : 게시글이 없어도 Exception이 아닐 수 있음.
        assertFalse(validBoard.isPresent());
    }

    @Test
    void 게시판_Title_정상_조회() {
        //given
        BoardEntity saveBoard = boardRepository.save(BoardEntity.of("제목", "본문", "자유게시판", false, "user1"));

        //when
        List<BoardEntity> validBoard = boardRepository.findByTitleAndDeletedAtIsNull("제목");

        //then
        assertEquals(saveBoard.getTitle(), validBoard.get(0).getTitle());
    }

    @Test
    void 계시판_Title_비정상_조회(){
        //given
        BoardEntity saveBoard = boardRepository.save(BoardEntity.of("정상", "본문", "자유게시판", false, "user1"));
        boardRepository.save(BoardEntity.of("비정상", "비정상본문", "자유게시판", false, "user1"));

        //when
        List<BoardEntity> validBoard = boardRepository.findByTitleAndDeletedAtIsNull("비정상");

        //then
        assertFalse(saveBoard.getContent().equals(validBoard.get(0).getContent()));
    }

    @Test
    void 계시판_삭제(){
        // 확인중
        //given
        BoardEntity saveBoard = boardRepository.save(BoardEntity.of("제목", "본문", "자유게시판", false, "user1"));

        //when
        boardService.delete(saveBoard.getId());
        entityManager.clear();

        //then
        assertThrows(NotFoundBoardException.class, () -> boardService.findById(saveBoard.getId()));
    }
}