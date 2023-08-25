package com.study.battleq.modules.quiz.controller.v1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.battleq.modules.quiz.domain.dto.CreateQuizRequest;
import com.study.battleq.modules.quiz.domain.entity.QuizType;
import com.study.battleq.modules.quiz.repository.QuizRepository;
import com.study.battleq.modules.quiz.service.QuizService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ActiveProfiles("local")
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@SqlGroup({
        @Sql(value = "/sql/quiz-controller-test-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
})
class QuizControllerV1Test {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    QuizService quizService;

    @Autowired
    QuizRepository quizRepository;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void 퀴즈를_정상적으로_조회한다() throws Exception {
        //given
        //when
        //then
        mockMvc.perform(get("/api/v1/quizzes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value(1))
                .andExpect(jsonPath("$.data.quizType").value(QuizType.SHORT_ANSWER.toString()))
                .andExpect(jsonPath("$.data.quizData").value("11"));
    }

    @Test
    void 존재하지_않는_퀴즈ID로_조회할_경우_404_응답을_한다() throws Exception {
        //given
        //when
        //then
        mockMvc.perform(get("/api/v1/quizzes/123456789"))
                .andExpect(status().isNotFound());
    }


    @Test
    void 퀴즈가_정상적으로_생성된다() throws Exception {

        //실패하는 이유?

        mockMvc.perform(post("/api/v1/quizzes")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(CreateQuizRequest.of(QuizType.SHORT_ANSWER, "question","answer","description",null))))
                .andDo(print())
                .andExpect(status().isOk());


    }

    @Test
    void deleteQuiz() {
    }
}