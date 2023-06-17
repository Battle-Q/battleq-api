package com.study.battleq.modules.user.domain.repository;

import ch.qos.logback.classic.spi.STEUtil;
import com.study.battleq.modules.user.domain.entity.Authority;
import com.study.battleq.modules.user.domain.entity.UserEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("local")
class UserCommandServiceTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserCommandService userCommandService;

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void 저장_성공() {
        //given
        UserEntity userEntity = UserEntity.of("email", "name", "password", "nick", Authority.ROLE_STUDENT);
        //when
        userCommandService.save(userEntity);
        //then
        assertTrue(userRepository.findByEmailAndDeletedAtIsNull("email").isPresent());
    }
}