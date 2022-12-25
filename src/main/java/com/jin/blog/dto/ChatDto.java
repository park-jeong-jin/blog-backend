package com.jin.blog.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class ChatDto {

  @Getter
  @AllArgsConstructor(access = AccessLevel.PUBLIC)
  public static class ChatResponse {
    String username;
    String message;
  }

  @Getter
  public static class ChatRequest {
    String username;
    String message;

    @Builder
    private ChatRequest(
        String username,
        String message
    ) {
      this.username = username;
      this.message = message;
    }
  }
}
