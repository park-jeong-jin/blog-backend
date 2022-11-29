package com.jin.blog.domain;

import com.jin.blog.dto.UserDto;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class User {

  @Id
  @GeneratedValue
  private Long id;
  @NotNull
  private String username;
  @NotNull
  private String password;
  private Boolean activated;
  @Enumerated(EnumType.STRING)
  private Role role;

  @Getter
  public enum Role {
    ADMIN("관리자"),
    NORMAL("일반사용자");
    private String desc;

    Role(String desc) {
      this.desc = desc;
    }
  }

  @Getter
  public static class Auth implements GrantedAuthority {
    private String auth;

    public Auth(String auth) {
      this.auth = auth;
    }

    @Override
    public String getAuthority() {
      return this.getAuth();
    }
  }

  public static UserDto.UserResponse toDto(User user) {
    return UserDto.UserResponse.builder().user(user).build();
  }

  public void update(UserDto.UserRequest dto) {
    this.activated = dto.getActivated();
    this.role = dto.getRole();
  }
}
