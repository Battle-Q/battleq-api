package com.study.battleq.modules.board.domain.repository;

import com.study.battleq.modules.board.domain.entity.BoardEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Repository Layer Test
 * 데이터에 대한 조작 및 조회(CRUD)를 담당하는 Layer이므로, Repository의 동작(저장 및 조회)에 대해 테스트 한다.
 * 저장을 위한 JPA 연관 관계가 적절히 구성되었는지, Repository 메소드가 제대로 구현되었는지 확인하는 것을 목저으로 한다.
 * <p>
 * 1. 게시판 저장
 * 2. 게시판 단건(Id) 조회
 * 3. 게시판 단건(Title) 조회
 * 4. 게시판 전체 조회
 * 5. 게시판 단건 업데이트
 * 6. 게시판 삭제
 */
@SpringBootTest
@ActiveProfiles("local")
class BoardRepositoryTest {

    private String testTitle = "테스트";
    private String testContent = "내용";
    private String testCategory = "자유게시판";
    private String testDumpUser = "user1";
    private BoardRepository boardRepository;

    @Autowired
    public BoardRepositoryTest(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @PersistenceContext
    EntityManager entityManager;

    @AfterEach
    void 게시판_전체_삭제() {
        //boardRepository.deleteAll();
    }

    @BeforeEach
    void 게시판_초기화() {
        boardRepository.save(BoardEntity.of(testTitle, testContent, testCategory, false, testDumpUser));
    }

    @Test
    void 게시판_저장(){
        //given

        //when
        BoardEntity saveBoard = boardRepository.save(BoardEntity.of("게시판 저장", testContent, testCategory, false, testDumpUser));

        //then
        assertEquals(saveBoard.getTitle(), "게시판 저장");
    }
    @Test
    void 계시판_비정상_조회_Id() {
        //given

        //when
        Long wrongId = 9999L;
        Optional<BoardEntity> validBoard = boardRepository.findByIdAndDeletedAtIsNull(wrongId);

        //then : 게시글이 없어도 Exception이 아닐 수 있음.
        assertFalse(validBoard.isPresent());
    }

    @Test
    void 게시판_정상_조회_Id() {
        //given
        BoardEntity saveBoard = boardRepository.save(BoardEntity.of("새로운 게시글", "본문", "자유게시판", false, "user1"));

        //when
        Optional<BoardEntity> validBoard = boardRepository.findByIdAndDeletedAtIsNull(saveBoard.getId());

        //then
        assertEquals(saveBoard.getId(), validBoard.get().getId());
    }

    @Test
    void 계시판_비정상_조회_Title() {
        //given

        //when
        List<BoardEntity> validBoard = boardRepository.findByTitleAndDeletedAtIsNull("잘못된 제목");

        //then
        assertFalse(validBoard.size() > 0);
    }

    @Test
    void 게시판_정상_조회_Title() {
        //given

        //when
        List<BoardEntity> validBoard = boardRepository.findByTitleAndDeletedAtIsNull("테스트");

        //then
        assertEquals(validBoard.get(0).getTitle(), testTitle);
    }

    @Test
    void 게시판_전체_조회(){
        //given

        //when
        List<BoardEntity> validBoard = boardRepository.findAllByDeletedAtIsNull();

        //given
        assertEquals(validBoard.get(0).getId() , 1L);
        assertEquals(validBoard.get(0).getTitle() , testTitle);
    }

    @Test
    void 게시판_단건_업데이트(){
        //given

        //when
        Optional<BoardEntity> board = boardRepository.findByIdAndDeletedAtIsNull(1L);
        board.get().updateBoard("변경한 본문", testCategory, false);

        board = boardRepository.findByIdAndDeletedAtIsNull(1L);
        System.out.println("board = " + board.get().getContent());


    }
    @Test
    void 계시판_삭제() {
        // 확인중
        //given
        BoardEntity saveBoard = boardRepository.save(BoardEntity.of("제목", "본문", "자유게시판", false, "user1"));

        //when
        //boardService.delete(saveBoard.getId());
        entityManager.clear();

        //then
        //assertThrows(BoardNotFoundException.class, () -> boardService.findById(saveBoard.getId()));
    }
}