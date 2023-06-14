package com.study.battleq.modules.user.domain.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class UserRedisRepository {

    private static final String REFRESH_TOKEN_PREFIX = "REFRESH_TOKEN:";
    private final RedisTemplate<String, String> redisTemplate;

    public Optional<String> findByEmail(String userId) {
        return Optional.ofNullable(redisTemplate.opsForValue().get(REFRESH_TOKEN_PREFIX + userId));
    }

    public void save(String key, String value, Long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(REFRESH_TOKEN_PREFIX + key, value, timeout, unit);
    }

}
