package com.jin.blog.dto;

import lombok.Builder;
import lombok.Getter;

public class TokenDto {

  @Getter
  public static class TokenResponse {
    private String name;
    private String role;
    private String token;

    @Builder
    public TokenResponse(
        String name,
        String role,
        String token
    ) {
      this.name = name;
      this.role = role;
      this.token = token;
    }
  }
}
