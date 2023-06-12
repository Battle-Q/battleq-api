package com.study.battleq.modules.board.controller;

import com.study.battleq.modules.board.domain.entity.BoardEntity;
import com.study.battleq.modules.board.domain.repository.BoardRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static java.time.Duration.ofMinutes;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.condition.OS.MAC;

@SpringBootTest
@ActiveProfiles("local")
@AutoConfigureMockMvc
public class BoardApiControllerTest {
    private static final String TITLE = "title";
    private static final String CONTENT = "content";
    private static final String CATEGORY = "freeBoard";
    private static final boolean PRIORITY = false;
    private static final String WRITER = "user";

    @Autowired
    MockMvc mockMvc;

    @Autowired
    BoardRepository boardRepository;

    @PersistenceContext
    EntityManager entityManager;

    @Test
    @DisplayName("게시판 저장 성공 완료 테스트")
    void 게시판_저장_성공() throws Exception {
        //given
        BoardEntity bookEntity = BoardEntity.builder()
                .title(TITLE)
                .content(CONTENT)
                .category(CATEGORY)
                .priority(PRIORITY)
                .writer(WRITER)
                .build();
        // 저장
        BoardEntity saveEntity = boardRepository.save(bookEntity);

        BoardEntity wrongEntity = BoardEntity.builder()
                .title("dump")
                .build();
        //then
        assertEquals(saveEntity, bookEntity, "추가 메세지 작성 가능");
    }

    @Test
    @DisplayName("게시판 검색 성공")
    void 게시판_검색_성공() throws Exception {
        //given
        String searchParam = "title";
        BoardEntity bookEntity = BoardEntity.builder()
                .title(TITLE)
                .content(CONTENT)
                .category(CATEGORY)
                .priority(PRIORITY)
                .writer(WRITER)
                .build();

        //when
        boardRepository.save(bookEntity);
        List<BoardEntity> boardEntity = boardRepository.findByTitle(searchParam);

        //then
        assertEquals(TITLE, boardEntity.get(0).getTitle());
    }


    // TODO 테스트 코드 익숙해지면 하단 코드들 삭제
    @Test
    @DisplayName("테스트코드 연습")
    void 테스트_코드_연습(){
        assertThrows(CustomException.class, () -> new CustomClass(1));
    }
    
    private class CustomException extends Exception {
        public CustomException(String message){
            super(message);
        }
    }

    public class CustomClass {
        private int customInt;

        public CustomClass(int customInt) throws CustomException {
            if(customInt > 2){
                throw new CustomException("2보다 큼");
            }
            this.customInt = customInt;
        }
    }

    @Test
    void 타임아웃() {
        assertTimeout(ofMinutes(2), () -> {
            // 2분 미만으로 실행되는 동작 실행

        });
        String actualResult = assertTimeout(ofMinutes(2), () -> {
            return "a result";
        });
        assertEquals("a result", actualResult);
    }

    @Test
    @DisabledOnOs(value = MAC,disabledReason = "맥에서는 테스트하지 말아요.")
    void OS_테스트(){

    }
    
    @ParameterizedTest
    @ValueSource(ints = {0,101,Integer.MAX_VALUE})
    void 파라미터_반복_테스트(int num) {
        Shape shape = new Shape();
        assertThrows(IllegalArgumentException.class,
                () -> shape.cal(num));
    }

    private class Shape{
        int param;

        public void cal(int num) {
            this.param = num;
            if(num < 1 || num > 100){
                throw new IllegalArgumentException("Exception...");
            }
        }
    }
}
