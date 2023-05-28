package com.study.battleq.modules.user.controller;

import com.study.battleq.infrastructure.common.dto.ResponseDto;
import com.study.battleq.modules.user.controller.request.LoginRequest;
import com.study.battleq.modules.user.service.dto.TokenDto;
import com.study.battleq.modules.user.service.usecase.LoginUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Tag(name = "Auth")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final LoginUseCase loginUseCase;

    @Operation(summary = "로그인", description = "로그인 후 토큰을 발급받습니다.")
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = "200",
                            description = "요청 성공",
                            content = @Content(schema = @Schema(implementation = TokenDto.class),
                                    examples = @ExampleObject(name = "정상", value = """
                                            {
                                              "data": {
                                                "accessToken": "",
                                                "refreshToken": ""
                                              },
                                              "status": "OK"
                                            }
                                            """))
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "요청 실패",
                            content = @Content(schema = @Schema(implementation = TokenDto.class),
                                    examples = @ExampleObject(name = "로그인 실패", value = """
                                            {
                                              "message": "로그인 실패, 아이디나 비밀번호를 확인해주세요.",
                                              "path": "/api/v1/auth/login",
                                              "method": "POST"
                                            }
                                            """))
                    )
            }
    )
    @PostMapping("/login")
    public ResponseDto<TokenDto> login(@RequestBody @Valid LoginRequest request) {
        return ResponseDto.ok(loginUseCase.login(request.email(), request.password()));
    }
}
