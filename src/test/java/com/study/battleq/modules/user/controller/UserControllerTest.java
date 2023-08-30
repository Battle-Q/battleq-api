package com.study.battleq.modules.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.battleq.modules.user.controller.request.UserSignupRequest;
import com.study.battleq.modules.user.domain.entity.Authority;
import com.study.battleq.modules.user.domain.entity.UserEntity;
import com.study.battleq.modules.user.domain.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("local")
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
      UserEntity userEntity = UserEntity.of("email", "이름", "pwd", "nick123", Authority.ROLE_STUDENT);
      userRepository.save(userEntity);
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void 이미_회원가입한_이메일() throws Exception {
        //given
        //when
        mockMvc.perform(post("/api/v1/users")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new UserSignupRequest("email", "이름", "pwd", "nick"))))
                .andDo(print())
                //then
                .andExpect(status().isBadRequest());
    }

    @Test
    void 닉네임_중복() throws Exception {
        //given
        //when
        mockMvc.perform(post("/api/v1/users")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new UserSignupRequest("email11", "이름", "pwd", "nick123"))))
                .andDo(print())
                //then
                .andExpect(status().isBadRequest());
    }

    @ParameterizedTest
    @MethodSource("validationTestStubbing")
    void 회원가입_항목들_비어있을_때_오류_발생(UserSignupRequest request) throws Exception {
        //given
        //when
        mockMvc.perform(post("/api/v1/users")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                //then
                .andExpect(status().isBadRequest());
    }

    @Test
    void 회원가입_성공() throws Exception {
        //given
        //when
        mockMvc.perform(post("/api/v1/users")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new UserSignupRequest("email11", "이름", "pwd", "nick1234"))))
                .andDo(print())
                //then
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails(value = "email",setupBefore = TestExecutionEvent.TEST_EXECUTION)
    void 탈퇴_성공() throws Exception {
        //given
        //when
        mockMvc.perform(post("/api/v1/users/withdraw")
                .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            //then
            .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails(value = "email", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    void me_api_조회() throws Exception {
        //given
        //when
        mockMvc.perform(get("/api/v1/users/me"))
            .andDo(print())
        //then
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data.name").value("이름"))
            .andExpect(jsonPath("$.data.nickname").value("nick123"))
            .andExpect(jsonPath("$.data.authority").value("ROLE_STUDENT"));
    }

    private static Stream<Arguments> validationTestStubbing() {
        return Stream.of(
                Arguments.of(new UserSignupRequest(null, "name", "pwd", "nick")),
                Arguments.of(new UserSignupRequest("email", null, "pwd", "nick")),
                Arguments.of(new UserSignupRequest("email", "name", null, "nick")),
                Arguments.of(new UserSignupRequest("email", "name", "pwd", null)));
    }


}