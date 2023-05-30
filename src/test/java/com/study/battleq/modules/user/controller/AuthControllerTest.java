package com.study.battleq.modules.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.battleq.modules.user.controller.request.LoginRequest;
import com.study.battleq.modules.user.domain.entity.Authority;
import com.study.battleq.modules.user.domain.entity.UserEntity;
import com.study.battleq.modules.user.domain.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("local")
@AutoConfigureMockMvc
class AuthControllerTest {

    private static final String EMAIL = "test@battleq.com";
    private static final String PASSWORD = "battleq";

    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    ObjectMapper objectMapper;

    @PersistenceContext
    EntityManager entityManager;

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("로그인 성공")
    void 로그인_성공() throws Exception {
        //given
        userRepository.save(UserEntity.of(EMAIL, "테스트", passwordEncoder.encode(PASSWORD), "배틀큐", Authority.ROLE_STUDENT));
        entityManager.clear();
        //when
        mockMvc.perform(post("/api/v1/auth/login")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new LoginRequest(EMAIL, PASSWORD))))
                .andDo(print())
                //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.accessToken").exists())
                .andExpect(jsonPath("$.data.refreshToken").exists());
    }

    @Test
    @DisplayName("아이디가 틀렸을 때 401")
    void 아이디가_틀렸을_때_로그인_실패_401() throws Exception {
        //given
        userRepository.save(UserEntity.of(EMAIL, "테스트", passwordEncoder.encode(PASSWORD), "배틀큐", Authority.ROLE_STUDENT));
        entityManager.clear();
        //when
        mockMvc.perform(post("/api/v1/auth/login")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new LoginRequest(EMAIL + 1, PASSWORD))))
                .andDo(print())
                //then
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("비밀번호가 틀렸을 때 401")
    void 비밀번호가_틀렸을_때_로그인_실패_401() throws Exception {
        //given
        userRepository.save(UserEntity.of(EMAIL, "테스트", passwordEncoder.encode(PASSWORD), "배틀큐", Authority.ROLE_STUDENT));
        entityManager.clear();
        //when
        mockMvc.perform(post("/api/v1/auth/login")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new LoginRequest(EMAIL, PASSWORD + 1))))
                .andDo(print())
                //then
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("존재하지 않는 회원일 때 401")
    void 존재하지_않는_회원일_때_401() throws Exception {
        //given
        //when
        mockMvc.perform(post("/api/v1/auth/login")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new LoginRequest(EMAIL, PASSWORD + 1))))
                .andDo(print())
                //then
                .andExpect(status().isUnauthorized());
    }
}