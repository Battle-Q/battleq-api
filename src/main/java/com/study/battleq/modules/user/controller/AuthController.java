package com.study.battleq.modules.user.controller;

import com.study.battleq.infrastructure.common.dto.ResponseDto;
import com.study.battleq.modules.user.controller.request.LoginRequest;
import com.study.battleq.modules.user.controller.request.RefreshTokenRequest;
import com.study.battleq.modules.user.service.AuthService;
import com.study.battleq.modules.user.service.dto.TokenDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Auth")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

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
    return ResponseDto.ok(authService.login(request.email(), request.password()));
  }

  @Operation(summary = "리프레쉬 토큰", description = "토큰을 리프레쉬 토큰을 사용하여 재발급 받습니다.")
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
                  examples = {@ExampleObject(name = "리프레쉬 토큰 시간 만료", value = """
                      {
                        "message": "토큰의 유효시간이 만료되었습니다.",
                        "path": "/api/v1/auth/refresh",
                        "method": "POST"
                      }
                      """),
                      @ExampleObject(name = "리프레쉬 토큰 불일치", value = """
                          {
                            "message": "RefreshToken이 일치하지 않습니다.",
                            "path": "/api/v1/auth/refresh",
                            "method": "POST"
                          }
                          """)
                  })
          )
      }
  )
  @PostMapping("/refresh")
  public ResponseDto<TokenDto> refresh(@RequestBody @Valid RefreshTokenRequest request) {
    return ResponseDto.ok(authService.refresh(request.accessToken(), request.refreshToken()));
  }
}
