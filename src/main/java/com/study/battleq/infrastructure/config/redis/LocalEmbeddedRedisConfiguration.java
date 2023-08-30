package com.study.battleq.infrastructure.config.redis;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.StringUtils;
import redis.embedded.RedisServer;

@Configuration
@ConditionalOnProperty(name = "redis.embedded.enabled", havingValue = "true")
public class LocalEmbeddedRedisConfiguration {

  @Value("${spring.redis.port}")
  private int redisPort;

  private RedisServer redisServer;

  @PostConstruct
  public void redisServer() throws Exception {
    int port = isRedisRunning() ? findAvailablePort() : redisPort;
    redisServer = getRedisServer(port);
    redisServer.start();
  }

  private RedisServer getRedisServer(int port) throws IOException {
    if (isArmMac()) {
      return new RedisServer(getRedisFileForArmMac(), port);
    }
    return RedisServer.builder().port(port).setting("maxmemory 128M").build();
  }

  @PreDestroy
  public void stopRedis() {
    if (redisServer != null) {
      redisServer.stop();
    }
  }

  private boolean isArmMac() {
    return System.getProperty("os.arch").equals("aarch64") &&
        System.getProperty("os.name").equals("Mac OS X");
  }

  private File getRedisFileForArmMac() throws IOException {
    File file = new ClassPathResource("redis/redis-server-m1-mac").getFile();
    return file;
  }

  /**
   * Embedded Redis가 현재 실행중인지 확인
   */
  private boolean isRedisRunning() throws IOException {
    return isRunning(executeGrepProcessCommand(redisPort));
  }

  /**
   * 현재 PC/서버에서 사용가능한 포트 조회
   */
  public int findAvailablePort() throws IOException {

    for (int port = 10000; port <= 65535; port++) {
      Process process = executeGrepProcessCommand(port);
      if (!isRunning(process)) {
        return port;
      }
    }

    throw new IllegalArgumentException("Not Found Available port: 10000 ~ 65535");
  }

  /**
   * 해당 port를 사용중인 프로세스 확인하는 sh 실행
   */
  private Process executeGrepProcessCommand(int port) throws IOException {
    return Runtime.getRuntime().exec(getShellScriptByOs(port));
  }

  private String[] getShellScriptByOs(int port) {
    String command = String.format("netstat -nat | grep LISTEN|grep %d", port);
    boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
    if (isWindows) {
      return new String[]{"cmd.exe", "-c", command};
    }
    return new String[]{"/bin/sh", "-c", command};
  }

  /**
   * 해당 Process가 현재 실행중인지 확인
   */
  private boolean isRunning(Process process) {
    String line;
    StringBuilder pidInfo = new StringBuilder();

    try (BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()))) {

      while ((line = input.readLine()) != null) {
        pidInfo.append(line);
      }

    } catch (Exception e) {
    }

    return !StringUtils.isEmpty(pidInfo.toString());
  }

  @Bean
  public RedisConnectionFactory redisConnectionFactory() {
    return new LettuceConnectionFactory();
  }

  @Bean
  public RedisTemplate<?, ?> redisTemplate() {
    RedisTemplate<?, ?> template = new RedisTemplate<>();
    template.setConnectionFactory(redisConnectionFactory());
    template.setKeySerializer(new StringRedisSerializer());
    return template;
  }
}
