package com.study.battleq.modules.user.controller;

import com.study.battleq.infrastructure.common.dto.ResponseDto;
import com.study.battleq.modules.user.controller.request.UserSignupRequest;
import com.study.battleq.modules.user.service.dto.UserSignupCommand;
import com.study.battleq.modules.user.service.usecase.UserSignupUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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

    @PostMapping("")
    public ResponseDto<Void> signup(@RequestBody @Valid UserSignupRequest request) {
        UserSignupCommand command = UserSignupCommand.of(request.email(), request.name(), request.password(), request.nickname());
        userSignupUseCase.signup(command);
        return ResponseDto.ok();
    }
}
