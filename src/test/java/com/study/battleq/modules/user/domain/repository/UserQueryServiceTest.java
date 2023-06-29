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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    void 이메일_중복_확인() {
        //given
        String email = "email@email.com";
        userRepository.save(UserEntity.of(email, "name", "pwd", "nickName", Authority.ROLE_ADMIN));
        entityManager.clear();
        //when
        //then
        assertTrue(userQueryService.hasDuplicateEmail(email));
    }

    @Test
    void 이메일_중복이_아닐_때_false() {
        //given
        //when
        //then
        assertFalse(userQueryService.hasDuplicateEmail("email"));
    }

    @Test
    void 닉네임_중복() {
        //given
        String email = "email@email.com";
        String nickName = "nickName";
        userRepository.save(UserEntity.of(email, "name", "pwd", nickName, Authority.ROLE_ADMIN));
        entityManager.clear();
        //when
        //then
        assertTrue(userQueryService.hasDuplicateNickname(nickName));
    }

    @Test
    void 존재하지_않는_닉네임() {
        //given
        //when
        //then
        assertFalse(userQueryService.hasDuplicateNickname("nick123"));
    }

    @Test
    void id로_조회() {
        //given
        Long id = userRepository.save(UserEntity.of("email", "name", "pwd", "nickName", Authority.ROLE_ADMIN)).getId();
        //when
        UserEntity entity = userQueryService.findById(id);
        //then
        assertNotNull(entity);
    }

    @Test
    void id로_조회_실패() {
        //given
        //when
        //then
        assertThrows(UserNotFoundException.class, () -> userQueryService.findById(123123L));
    }
}