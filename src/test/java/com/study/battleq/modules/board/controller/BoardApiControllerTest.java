package com.study.battleq.modules.board.controller;

import com.study.battleq.board.domain.entity.BoardEntity;
import com.study.battleq.board.repository.BoardRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("local")
@AutoConfigureMockMvc
public class BoardApiControllerTest {
    private static final String TITLE = "title";
    private static final String CONTENT = "content";
    private static final String CATEGORY = "freeBoard";
    private static final boolean IMPORTANCE = false;
    private static final String WRITER = "user";

    @Autowired
    MockMvc mockMvc;

    @Autowired
    BoardRepository boardRepository;

    @PersistenceContext
    EntityManager entityManager;

    @Test
    @DisplayName("게시판 저장 성공")
    void 게시판_저장_성공() throws Exception {
        System.out.println("◼◼◼◼◼ BoardEntity Save Start... ◼◼◼◼◼ ︎");

        //given
        BoardEntity bookEntity = BoardEntity.builder()
                .title(TITLE)
                .content(CONTENT)
                .category(CATEGORY)
                .importance(IMPORTANCE)
                .writer(WRITER)
                .build();
        // 저장
        BoardEntity saveEntity = boardRepository.save(bookEntity);

        BoardEntity wrongEntity = BoardEntity.builder()
                .title("dump")
                .build();
        //then
        assertEquals(saveEntity, bookEntity);
    }

    @Test
    @DisplayName("게시판 검색 성공")
    void 게시판_검색_성공() throws Exception {
        System.out.println("◼◼◼◼◼ BoardEntity Search Start... ◼◼◼◼◼ ︎");

        //given
        String searchParam = "title";
        BoardEntity bookEntity = BoardEntity.builder()
                .title(TITLE)
                .content(CONTENT)
                .category(CATEGORY)
                .importance(IMPORTANCE)
                .writer(WRITER)
                .build();

        //when
        boardRepository.save(bookEntity);
        List<BoardEntity> boardEntity = boardRepository.findByTitle(searchParam);

        //then
        assertEquals(TITLE, boardEntity.get(0).getTitle());
    }
}
