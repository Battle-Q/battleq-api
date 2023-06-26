package com.study.battleq.modules.user.domain.repository;

import com.study.battleq.modules.user.domain.entity.Authority;
import com.study.battleq.modules.user.domain.entity.UserEntity;
import com.study.battleq.modules.user.domain.repository.exception.UserNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("local")
class UserQueryServiceTest {

    @Autowired
    private UserQueryService userQueryService;

    @Autowired
    private UserRepository userRepository;

    @PersistenceContext
    EntityManager entityManager;

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("유저가 정상 조회 되는지 확인")
    void 유저_정상_조회_확인() {
        //given
        String email = "email@email.com";
        userRepository.save(UserEntity.of(email, "name", "pwd", "nickName", Authority.ROLE_ADMIN));
        entityManager.clear();
        //when
        UserEntity entity = userQueryService.findByEmail(email);
        //then
        assertEquals("name", entity.getName());
        assertEquals("nickName", entity.getNickname());
    }

    @Test
    @DisplayName("없는 회원을 조회할 때 UserNotFoundException")
    void 없는_회원을_조회할_때_UserNotFoundException_발생() {
        //given
        //when
        //then
        assertThrows(UserNotFoundException.class, ()-> userQueryService.findByEmail("email"));
    }

    @Test
    void 닉네임으로_조회_성공() {
        //given
        String email = "email@email.com";
        String nickName = "nickName";
        userRepository.save(UserEntity.of(email, "name", "pwd", nickName, Authority.ROLE_ADMIN));
        entityManager.clear();
        //when
        UserEntity entity = userQueryService.findByNickname(nickName);
        //then
        assertEquals("name", entity.getName());
        assertEquals(nickName, entity.getNickname());
    }

    @Test
    void 존재하지_않는_닉네임() {
        //given
        //when
        //then
        assertThrows(UserNotFoundException.class, () -> userQueryService.findByNickname("nick123"));
    }
}