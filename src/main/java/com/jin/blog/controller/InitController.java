package com.jin.blog.controller;

import com.jin.blog.domain.User;
import com.jin.blog.dto.BoardDto;
import com.jin.blog.dto.MenuDto;
import com.jin.blog.dto.UserDto;
import com.jin.blog.service.BoardService;
import com.jin.blog.service.MenuService;
import com.jin.blog.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "테스트", description = "테스트 api 입니다.")
@RestController
@RequestMapping("/init")
public class InitController {

  @Autowired
  UserService userService;
  @Autowired
  MenuService menuService;
  @Autowired
  BoardService boardService;

  @Operation(summary = "메뉴 저장", description = "메뉴 저장입니다.")
  @PostMapping("")
  @ResponseBody
  @ResponseStatus(value = HttpStatus.OK)
  public void init() {
    userService.create(UserDto.UserRequest.builder().username("admin").password("admin").role(User.Role.ADMIN).build());

    for (Integer i = 0; i < 2; i++) {
      MenuDto.MenuRequest dto1 = MenuDto.MenuRequest.builder()
          .sequence(i + 1)
          .name("대메뉴-" + (i + 1))
          .type("GROUP")
          .build();
      Long id1 = menuService.create(dto1);
      for (Integer j = 0; j < 2; j++) {
        MenuDto.MenuRequest dto2 = MenuDto.MenuRequest.builder()
            .sequence(j + 1)
            .name("중메뉴-" + (j + 1))
            .type("GROUP")
            .parentId(id1)
            .build();
        Long id2 = menuService.create(dto2);
        for (Integer k = 0; k < 3; k++) {
          MenuDto.MenuRequest dto3 = MenuDto.MenuRequest.builder()
              .sequence(k + 1)
              .name("소메뉴-" + (k + 1))
              .type("GROUP")
              .parentId(id2)
              .build();
          Long id3 = menuService.create(dto3);

          for (Integer l = 1; l <= 100 && i == 0 && j == 0; l++) {
            BoardDto.BoardRequest dto = BoardDto.BoardRequest.builder()
                .menuId(id3)
                .title("오늘도 개발중 - " + l)
                .content("자동입력입니다." + l)
                .writer("Swagger")
                .build();
            Long boardId = boardService.create(dto);
          }
        }
      }
    }
  }
}
