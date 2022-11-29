package com.jin.blog.controller;

import com.jin.blog.domain.User;
import com.jin.blog.dto.BoardDto;
import com.jin.blog.dto.CategoryDto;
import com.jin.blog.dto.UserDto;
import com.jin.blog.service.BoardService;
import com.jin.blog.service.CategoryService;
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
  CategoryService categoryService;
  @Autowired
  BoardService boardService;

  @Operation(summary = "메뉴 저장", description = "메뉴 저장입니다.")
  @PostMapping("")
  @ResponseBody
  @ResponseStatus(value = HttpStatus.OK)
  public void init() {
    userService.create(UserDto.UserRequest.builder().username("admin").password("admin").role(User.Role.ADMIN).build());

    for (Integer i = 0; i < 2; i++) {
      CategoryDto.CategoryRequest dto1 = CategoryDto.CategoryRequest.builder()
          .sequence(i + 1)
          .name("대메뉴-" + (i + 1))
          .type("GROUP")
          .build();
      Long id1 = categoryService.create(dto1);
      for (Integer j = 0; j < 2; j++) {
        CategoryDto.CategoryRequest dto2 = CategoryDto.CategoryRequest.builder()
            .sequence(j + 1)
            .name("중메뉴-" + (j + 1))
            .type("GROUP")
            .parentId(id1)
            .build();
        Long id2 = categoryService.create(dto2);
        for (Integer k = 0; k < 3; k++) {
          CategoryDto.CategoryRequest dto3 = CategoryDto.CategoryRequest.builder()
              .sequence(k + 1)
              .name("소메뉴-" + (k + 1))
              .type("GROUP")
              .parentId(id2)
              .build();
          Long id3 = categoryService.create(dto3);

          for (Integer l = 1; l <= 100 && i == 0 && j == 0; i++) {
            BoardDto.BoardRequest dto = BoardDto.BoardRequest.builder()
                .categoryId(id3)
                .title("오늘도 개발중 - " + i)
                .content("자동입력입니다." + i)
                .writer("Swagger")
                .build();
            Long boardId = boardService.create(dto);
          }
        }
      }
    }
  }
}
