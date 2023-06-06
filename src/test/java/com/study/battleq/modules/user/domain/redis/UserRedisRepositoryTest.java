package com.study.battleq.modules.user.domain.redis;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("local")
class UserRedisRepositoryTest {

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Autowired
    UserRedisRepository userRedisRepository;

    @AfterEach
    void tearDown() {
        redisTemplate.keys("*").stream().forEach(k -> {
            redisTemplate.delete(k);
        });
    }

    @Test
    void 조회_실패() {
        //given
        //when
        Optional<String> result = userRedisRepository.findByUserId("1");
        //then
        assertFalse(result.isPresent());
    }

    @Test
    void 조회_성공() {
        //given
        redisTemplate.opsForValue().set("REFRESH_TOKEN:1", "token");
        //when
        Optional<String> result = userRedisRepository.findByUserId("1");
        //then
        assertTrue(result.isPresent());
    }

    @Test
    void 저장_성공() {
        //given
        //when
        userRedisRepository.save("1", "token", 10000L, TimeUnit.MINUTES);
        //then
        Optional<String> result = userRedisRepository.findByUserId("1");
        assertTrue(result.isPresent());
        assertEquals("token", result.get());
    }
}