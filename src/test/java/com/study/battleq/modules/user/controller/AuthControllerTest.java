package com.study.battleq.modules.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.battleq.modules.user.controller.request.LoginRequest;
import com.study.battleq.modules.user.controller.request.RefreshTokenRequest;
import com.study.battleq.modules.user.domain.entity.Authority;
import com.study.battleq.modules.user.domain.entity.UserEntity;
import com.study.battleq.modules.user.domain.repository.UserRepository;
import com.study.battleq.modules.user.service.AuthService;
import com.study.battleq.modules.user.service.dto.TokenDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
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

    @Autowired
    AuthService authService;

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
        redisTemplate.keys("*").forEach(k -> redisTemplate.delete(k));
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

    @Test
    void 리프레쉬_토큰으로_정상_토큰_발급() throws Exception {
        //given
        userRepository.save(UserEntity.of(EMAIL, "테스트", passwordEncoder.encode(PASSWORD), "배틀큐", Authority.ROLE_STUDENT));
        entityManager.clear();
        TokenDto tokenDto = authService.login(EMAIL, PASSWORD);
        //when
        mockMvc.perform(post("/api/v1/auth/refresh")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new RefreshTokenRequest(tokenDto.getAccessToken(), tokenDto.getRefreshToken()))))
                .andDo(print())
                //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.accessToken").exists())
                .andExpect(jsonPath("$.data.refreshToken").exists());
    }

    @Test
    void 리프레쉬_토큰이_일치하지_않을_때() throws Exception {
        //given
        userRepository.save(UserEntity.of(EMAIL, "테스트", passwordEncoder.encode(PASSWORD), "배틀큐", Authority.ROLE_STUDENT));
        entityManager.clear();
        TokenDto tokenDto = authService.login(EMAIL, PASSWORD);
        //when
        mockMvc.perform(post("/api/v1/auth/refresh")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new RefreshTokenRequest(tokenDto.getAccessToken(), "eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE2ODY2NTcyMzJ9.3Fw9Jg_mdqPHoDL_H2KF73k4Hp70NVffPWie8-EP5A-DHoJfh_jsYgmUv5jydMQSGuKD6Xeo_aA_sPjNJA0rkA"))))
                .andDo(print())
                //then
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("RefreshToken이 일치하지 않습니다."));
    }

    @Test
    void 리프레쉬_토큰이_만료_되었을_때() throws Exception {
        //given
        UserEntity entity = userRepository.save(UserEntity.of(EMAIL, "테스트", passwordEncoder.encode(PASSWORD), "배틀큐", Authority.ROLE_STUDENT));
        entityManager.clear();
        TokenDto tokenDto = authService.login(EMAIL, PASSWORD);
        redisTemplate.keys("REFRESH_TOKEN:" + EMAIL).forEach(k -> redisTemplate.delete(k));
        //when
        mockMvc.perform(post("/api/v1/auth/refresh")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new RefreshTokenRequest(tokenDto.getAccessToken(), tokenDto.getRefreshToken()))))
                .andDo(print())
                //then
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message").value("토큰의 유효시간이 만료되었습니다."));
    }
}