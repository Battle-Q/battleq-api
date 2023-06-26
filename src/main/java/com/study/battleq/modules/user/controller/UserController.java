package com.study.battleq.modules.user.controller;

import com.study.battleq.infrastructure.common.dto.ResponseDto;
import com.study.battleq.modules.user.controller.request.UserSignupRequest;
import com.study.battleq.modules.user.domain.entity.BattleQUser;
import com.study.battleq.modules.user.service.dto.TokenDto;
import com.study.battleq.modules.user.service.dto.UserSignupCommand;
import com.study.battleq.modules.user.service.usecase.UserSignupUseCase;
import com.study.battleq.modules.user.service.usecase.UserWithdrawUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Tag(name = "User")
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

  private final UserSignupUseCase userSignupUseCase;
  private final UserWithdrawUseCase userWithdrawUseCase;

  @Operation(summary = "회원가입", description = "회원가입을 진행합니다.")
  @ApiResponses(
      {
          @ApiResponse(
              responseCode = "200",
              description = "요청 성공",
              content = @Content(schema = @Schema(implementation = TokenDto.class),
                  examples = @ExampleObject(name = "정상", value = """
                      {
                        "data": null,
                        "status": "OK"
                      }
                      """))
          ),
          @ApiResponse(
              responseCode = "400",
              description = "요청 실패",
              content = @Content(schema = @Schema(implementation = TokenDto.class),
                  examples = {@ExampleObject(name = "필수 항목을 입력하지 않았을 때", value = """
                      {
                        "message": "E-Mail은 필수입니다.",
                        "path": "/api/v1/users",
                        "method": "POST"
                      }
                                          """),
                      @ExampleObject(name = "이메일이 중복일 때", value = """
                          {
                            "message": "이미 회원가입한 계정입니다.",
                            "path": "/api/v1/users",
                            "method": "POST"
                          }
                                                  """),
                      @ExampleObject(name = "닉네임이 중복일 때", value = """
                          {
                            "message": "이미 사용중인 닉네임입니다.",
                            "path": "/api/v1/users",
                            "method": "POST"
                          }
                          """)
                  })
          )
      }
  )
  @PostMapping("")
  public ResponseDto<Void> signup(@RequestBody @Valid UserSignupRequest request) {
    UserSignupCommand command = UserSignupCommand.of(request.email(), request.name(), request.password(), request.nickname());
    userSignupUseCase.signup(command);
    return ResponseDto.ok();
  }

  @Operation(summary = "회원탈퇴", description = "회원탈퇴를 진행합니다.")
  @ApiResponses(
      {
          @ApiResponse(
              responseCode = "200",
              description = "요청 성공",
              content = @Content(schema = @Schema(implementation = TokenDto.class),
                  examples = @ExampleObject(name = "정상", value = """
                      {
                        "data": null,
                        "status": "OK"
                      }
                      """))
          )
      }
  )
  @PostMapping("/withdraw")
  public ResponseDto<Void> withdraw(@AuthenticationPrincipal BattleQUser battleQUser) {
    userWithdrawUseCase.withdraw(battleQUser.getId());
    return ResponseDto.ok();
  }
}
