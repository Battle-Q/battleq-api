package com.study.battleq.modules.user.service;

import com.study.battleq.modules.user.domain.entity.Authority;
import com.study.battleq.modules.user.domain.entity.UserEntity;
import com.study.battleq.modules.user.domain.repository.UserQueryRepository;
import com.study.battleq.modules.user.service.dto.TokenDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("local")
class AuthServiceTest {

    @Autowired
    AuthService authService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @MockBean
    UserQueryRepository userQueryRepository;

    @Test
    void 로그인_처리() {
        //given
        String email = "a@b.c";
        String password = "pwd";
        String encodedPassword = passwordEncoder.encode(password);
        when(userQueryRepository.findByEmail(anyString())).thenReturn(UserEntity.of(email, "name", encodedPassword, "nick", Authority.ROLE_ADMIN));
        //when
        TokenDto tokenDto = authService.login(email, password);
        //then
        assertNotNull(tokenDto.getAccessToken());
        assertNotNull(tokenDto.getRefreshToken());
    }
}