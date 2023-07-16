package com.study.battleq.infrastructure.config.jasypt;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JasyptConfiguration {

  private static final String ALGORITHM = "PBEWithMD5AndDES";

  private final String encKey;

  public JasyptConfiguration(@Value("${jasypt.encryptor.password}") String encKey) {
    this.encKey = encKey;
  }

  @Bean
  public StringEncryptor jasyptStringEncryptor() {
    PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
    SimpleStringPBEConfig config = new SimpleStringPBEConfig();
    config.setPassword(encKey);
    config.setAlgorithm(ALGORITHM);
    encryptor.setConfig(config);
    return encryptor;
  }
}