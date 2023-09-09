package com.study.battleq.modules.user.service.dto;

import com.study.battleq.modules.user.domain.entity.Authority;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserResponseDto {

  private String name;
  private String nickname;
  private Authority authority;
}
