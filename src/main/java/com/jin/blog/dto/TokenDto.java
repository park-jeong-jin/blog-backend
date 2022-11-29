package com.jin.blog.dto;

import lombok.Builder;
import lombok.Getter;

public class TokenDto {

  @Getter
  public static class TokenResponse {
    private String token;

    @Builder
    public TokenResponse(
        String token
    ) {
      this.token = token;
    }
  }
}
