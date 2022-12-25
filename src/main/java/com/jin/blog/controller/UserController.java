package com.jin.blog.controller;

import com.jin.blog.domain.User;
import com.jin.blog.dto.TokenDto;
import com.jin.blog.dto.UserDto;
import com.jin.blog.provider.JwtTokenProvider;
import com.jin.blog.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Tag(name = "사용자관리", description = "사용자 관련 api 입니다.")
@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  UserService userService;
  @Autowired
  JwtTokenProvider jwtTokenProvider;

  @Operation(summary = "계정 조회", description = "계정 조회입니다.")
  @GetMapping("")
  public List<UserDto.UserResponse> findAll(
      @ModelAttribute UserDto.UserFilter dto
  ) {
    return userService.findAll(dto);
  }

  @Operation(summary = "계정 입력", description = "계정 입력입니다.")
  @PostMapping("")
  @ResponseBody
  @ResponseStatus(value = HttpStatus.CREATED)
  public Long create(
      @Parameter(description = "board")
      @RequestBody final UserDto.UserRequest dto
  ) {
    return userService.create(dto);
  }

  @Operation(summary = "계정 수정", description = "계정 수정입니다.")
  @PutMapping("")
  @ResponseBody
  @ResponseStatus(value = HttpStatus.OK)
  public Long update(
      @RequestBody final UserDto.UserRequest dto
  ) {
    return userService.update(dto);
  }

  @Operation(summary = "계정 삭제", description = "계정 삭제입니다.")
  @DeleteMapping("/{id}")
  @ResponseStatus(value = HttpStatus.OK)
  public void delete(
      @PathVariable("id") Long id
  ) {
    userService.delete(id);
  }

  @Operation(summary = "계정 로그인", description = "계정 로그인입니다.")
  @PostMapping("/login")
  public ResponseEntity<TokenDto.TokenResponse> login(
      @RequestBody UserDto.UserLoginRequest dto
  ) {
    try {
      User user = userService.login(dto);
      String token = jwtTokenProvider.createToken(user.getUsername(), user.getRole().name());
      TokenDto.TokenResponse tokenResponse = TokenDto.TokenResponse.builder().name(dto.getUsername()).role(user.getRole().name()).token(token).build();
      return ResponseEntity.ok(tokenResponse);
    } catch (EntityNotFoundException e) {
      return ResponseEntity.badRequest().body(null);
    }
  }

  @Operation(summary = "계정 로그인", description = "계정 로그인입니다.")
  @PostMapping("/token")
  public ResponseEntity<TokenDto.TokenResponse> login(
      @RequestBody String token
  ) {
    try {
      if (jwtTokenProvider.validateToken(token)) return ResponseEntity.badRequest().body(null);
      User user = userService.findByUsername(jwtTokenProvider.getUsername(token));
      String newToken = jwtTokenProvider.createToken(user.getUsername(), user.getRole().name());
      TokenDto.TokenResponse tokenResponse = TokenDto.TokenResponse.builder().name(user.getUsername()).role(user.getRole().name()).token(newToken).build();
      return ResponseEntity.ok(tokenResponse);
    } catch (EntityNotFoundException e) {
      return ResponseEntity.badRequest().body(null);
    }
  }

}
