package com.study.battleq.infrastructure.config.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@ConstructorBinding
@AllArgsConstructor
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    private Long accessTokenExpireTime;
    private Long refreshTokenExpireTime;
    private String secret;
}
