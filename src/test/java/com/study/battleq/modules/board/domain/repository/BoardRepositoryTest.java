package com.study.battleq.modules.board.domain.repository;

import com.study.battleq.modules.board.domain.entity.BoardEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
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
 *
 *
 *
 *
 * <p>
 * 1. 게시판 저장
 * 2. 게시판 단건(Id) 조회
 * 3. 게시판 단건(Title) 조회
 * 4. 게시판 전체 조회
 * 5. 게시판 단건 업데이트
 * 6. 게시판 삭제
 */

@DataJpaTest
@ActiveProfiles("local")
class BoardRepositoryTest {

    private static final String testTitle = "테스트";
    private String testContent = "내용";
    private String testCategory = "자유게시판";
    private String testDumpUser = "user1";
    @Autowired
    private BoardRepository boardRepository;

    @Test
    void 게시판_저장(){
        //given
        BoardEntity givenEntity = BoardEntity.of("게시판 저장", testContent, testCategory, false, testDumpUser);

        //when
        BoardEntity saveEntity = boardRepository.save(givenEntity);

        //then
        Optional<BoardEntity> resultEntity = boardRepository.findByIdAndDeletedAtIsNull(saveEntity.getId());
        assertTrue(resultEntity.isPresent());
    }
    @Test
    void 게시판_비정상_조회_Id() {
        //given

        //when
        Long wrongId = 9999L;
        Optional<BoardEntity> resultEntity = boardRepository.findByIdAndDeletedAtIsNull(wrongId);

        //then
        assertFalse(resultEntity.isPresent());
    }

    @Test
    void 게시판_정상_조회_Id() {
        //given
        BoardEntity saveEntity = boardRepository.save(BoardEntity.of("새로운 게시글", "본문", "자유게시판", false, "user1"));

        //when
        Optional<BoardEntity> validBoard = boardRepository.findByIdAndDeletedAtIsNull(saveEntity.getId());

        //then
        assertTrue(validBoard.isPresent());
        BoardEntity boardEntity = validBoard.get();

        assertEquals(saveEntity.getId(), boardEntity.getId());
    }

    @Test
    void 게시판_비정상_조회_Title() {
        //given

        //when
        List<BoardEntity> validBoard = boardRepository.findByTitleAndDeletedAtIsNull("잘못된 제목");

        //then
        assertTrue(validBoard.isEmpty());
    }

    @Test
    void 게시판_정상_조회_Title() {
        //given
        BoardEntity saveEntity = boardRepository.save(BoardEntity.of("테스트", "본문", "자유게시판", false, "user1"));

        //when
        List<BoardEntity> validBoard = boardRepository.findByTitleAndDeletedAtIsNull("테스트");

        //then
        assertEquals(validBoard.get(0).getTitle(), saveEntity.getTitle());
    }

    @Test
    void 게시판_전체_조회(){
        //given
        boardRepository.save(BoardEntity.of("테스트", "본문", "자유게시판", false, "user1"));

        //when
        List<BoardEntity> validBoard = boardRepository.findAllByDeletedAtIsNull();

        //given
        assertFalse(validBoard.isEmpty());
    }

    @Test
    void 게시판_단건_업데이트(){
        //given
        Long id = boardRepository.save(BoardEntity.of(testTitle, testContent, testCategory, false, testDumpUser)).getId();

        //when
        Optional<BoardEntity> board = boardRepository.findByIdAndDeletedAtIsNull(id);

        BoardEntity boardEntity = board.get();
        boardEntity.updateBoard("변경한 본문", testCategory, false);
        boardRepository.save(boardEntity);

        //then
        Optional<BoardEntity> resultEntity = boardRepository.findByIdAndDeletedAtIsNull(id);

        assertTrue(resultEntity.isPresent());
        assertEquals(resultEntity.get().getContent(), "변경한 본문");
    }
    @Test
    void 계시판_삭제() {
        //given
        BoardEntity entity = BoardEntity.of("제목", "본문", "자유게시판", false, "user1");

        //when
        entity.delete();
        BoardEntity saveBoard = boardRepository.save(entity);

        assertNotEquals(saveBoard.getDeletedAt(), null);
    }
}