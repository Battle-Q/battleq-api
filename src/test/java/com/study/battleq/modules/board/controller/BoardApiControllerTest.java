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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("local")
@AutoConfigureMockMvc
public class BoardApiControllerTest {

    private static final String TITLE = "title1";
    private static final String CONTENT = "content";
    private static final String CATEGORY = "freeBoard";
    private static final boolean IMPORTANCE = false;
    private static final String WRITER = "user1";

    @Autowired
    MockMvc mockMvc;

    @Autowired
    BoardRepository boardRepository;

    @PersistenceContext
    EntityManager entityManager;

    @Test
    @DisplayName("게시판 저장 성공 및 조회")
    void 게시판_저장_성공_및_조회() throws Exception {
        //given
        BoardEntity save = BoardEntity.builder()
                .title(TITLE)
                .content(CONTENT)
                .category(CATEGORY)
                .importance(IMPORTANCE)
                .writer(WRITER)
                .build();
        // 저장
        Long id = boardRepository.save(save).getId();
        entityManager.clear();
        //when
        Optional<BoardEntity> boardEntity = boardRepository.findById(id);
        if(!boardEntity.isPresent()){ // 조회값이 없을 경우
            assertThrows(Exception.class, () ->boardRepository.findById(id));
        }
        //then
        assertEquals(TITLE,boardEntity.get().getTitle());
    }
}
