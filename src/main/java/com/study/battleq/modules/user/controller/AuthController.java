package com.study.battleq.modules.user.controller;

import com.study.battleq.infrastructure.common.dto.ResponseDto;
import com.study.battleq.modules.user.controller.request.LoginRequest;
import com.study.battleq.modules.user.service.LoginUseCase;
import com.study.battleq.modules.user.service.dto.TokenDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
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
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class AuthController {

    private final LoginUseCase loginUseCase;

    @Operation(summary = "로그인", description = "로그인 후 토큰을 발급받습니다.")
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = "200",
                            description = "요청 성공",
                            content = @Content(schema = @Schema(implementation = TokenDto.class))
                    )
            }
    )
    @PostMapping("/login")
    public ResponseDto<TokenDto> login(@RequestBody @Valid LoginRequest request) {
        return ResponseDto.ok(loginUseCase.login(request.email(), request.password()));
    }
}
