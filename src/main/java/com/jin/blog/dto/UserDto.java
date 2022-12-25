package com.jin.blog.dto;

import com.jin.blog.domain.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserDto {

  @Getter
  @AllArgsConstructor(access = AccessLevel.PUBLIC)
  public static class UserResponse {
    Long id;
    String username;
    Boolean activated;
    User.Role role;

    @Builder
    private UserResponse(
        User user
    ) {
      this.id = user.getId();
      this.username = user.getUsername();
      this.activated = user.getActivated();
      this.role = user.getRole();
    }
  }

  @Getter
  public static class UserRequest {
    Long id;
    String username;
    String password;
    Boolean activated;
    User.Role role;

    @Builder
    private UserRequest(
        Long id,
        String username,
        String password,
        Boolean activated,
        User.Role role
    ) {
      this.username = username;
      this.password = password;
      this.activated = activated;
      this.role = role;
    }

    public User toEntity(
        PasswordEncoder passwordEncoder
    ) {
      String encPassword = passwordEncoder.encode(this.password);
      return User.builder()
          .id(this.id)
          .username(this.username)
          .password(encPassword)
          .activated(this.activated)
          .role(this.role)
          .build();
    }
  }

  @Getter
  public static class UserFilter {
    String username;

    @Builder
    public UserFilter(
        String username
    ) {
      this.username = username;
    }
  }

  @Getter
  public static class UserLoginRequest {
    String username;
    String password;

    @Builder
    public UserLoginRequest(
        String username,
        String password
    ) {
      this.username = username;
      this.password = password;
    }
  }
}
