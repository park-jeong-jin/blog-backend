package com.jin.blog.controller;

import com.jin.blog.dto.MenuDto;
import com.jin.blog.service.MenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "메뉴", description = "메뉴 관련 api 입니다.")
@RestController
@RequestMapping("/menu")
public class MenuController {

  @Autowired
  MenuService menuService;

  @Operation(summary = "메뉴 조회", description = "메뉴 조회입니다.")
  @GetMapping("")
  public List<MenuDto.MenuResponse> findAll(
      @ModelAttribute MenuDto.MenuFilter dto
  ) {
    return menuService.findAll(dto);
  }

  @Operation(summary = "메뉴 조회", description = "메뉴 조회입니다.")
  @GetMapping("/dw")
  public List<MenuDto.MenuDwResponse> findAllDw(
      @ModelAttribute MenuDto.MenuFilter dto
  ) {
    return menuService.findAllDw(dto);
  }

  @GetMapping("/{id}")
  public MenuDto.MenuResponse findById(
      @PathVariable("id") Long id
  ) {
    return menuService.find(id);
  }

  @Operation(summary = "메뉴 입력", description = "메뉴 입력입니다.")
  @PostMapping("")
  @ResponseBody
  @ResponseStatus(value = HttpStatus.CREATED)
  public Long create(
      @Parameter(description = "board")
      @RequestBody final MenuDto.MenuRequest dto
  ) {
    return menuService.create(dto);
  }

  @Operation(summary = "메뉴 수정", description = "메뉴 수정입니다.")
  @PutMapping("")
  @ResponseBody
  @ResponseStatus(value = HttpStatus.OK)
  public Long update(
      @RequestBody final MenuDto.MenuRequest dto
  ) {
    return menuService.update(dto);
  }

  @Operation(summary = "메뉴 삭제", description = "메뉴 삭제입니다.")
  @DeleteMapping("/{id}")
  @ResponseStatus(value = HttpStatus.OK)
  public void delete(
      @PathVariable("id") Long id
  ) {
    menuService.delete(id);
  }
}
