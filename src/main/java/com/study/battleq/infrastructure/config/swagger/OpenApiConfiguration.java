package com.study.battleq.infrastructure.config.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.In;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;
import io.swagger.v3.oas.models.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Profile("!prd")
@Configuration
@RequiredArgsConstructor
public class OpenApiConfiguration {

    private final Environment environment;

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(components())
                .servers(servers(getActiveProfiles()))
                .security(List.of(new SecurityRequirement().addList(AUTHORIZATION)))
                .info(info());
    }

    private Components components() {
        return new Components()
                .addResponses("200", new ApiResponse().description("요청 성공")
                        .content(new Content()))
                .addResponses("404",
                        new ApiResponse().description("요청한 리소스가 존재하지 않음")
                                .content(new Content()))
                .addSecuritySchemes("Authorization", securityScheme());
    }

    private List<Server> servers(String activeProfiles) {
        return switch (activeProfiles) {
            default -> List.of(server("http://localhost:8080", "local"));
        };
    }

    private Server server(String url, String environment) {
        return new Server().url(url)
                .description(environment);
    }

    private Info info() {
        return new Info()
                .title("Battle-Q API");
    }


    private SecurityScheme securityScheme() {
        return new SecurityScheme()
                .type(Type.HTTP)
                .scheme("Bearer")
                .in(In.HEADER)
                .bearerFormat("JWT")
                .name(AUTHORIZATION);
    }

    private String getActiveProfiles() {
        try {
            String activeProfile = environment.getActiveProfiles()[0];
            return activeProfile;
        } catch (Exception e) {
            return null;
        }
    }

}