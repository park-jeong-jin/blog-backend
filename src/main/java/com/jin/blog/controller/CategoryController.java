package com.jin.blog.controller;

import com.jin.blog.dto.CategoryDto;
import com.jin.blog.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "메뉴", description = "메뉴 관련 api 입니다.")
@RestController
@RequestMapping("/category")
public class CategoryController {

  @Autowired
  CategoryService categoryService;

  @Operation(summary = "메뉴 조회", description = "메뉴 조회입니다.")
  @GetMapping("")
  public List<CategoryDto.CategoryResponse> findAll(
      @ModelAttribute CategoryDto.CategoryFilter dto
  ) {
    return categoryService.findAll(dto);
  }

  @Operation(summary = "메뉴 조회", description = "메뉴 조회입니다.")
  @GetMapping("/dw")
  public List<CategoryDto.CategoryDwResponse> findAllDw(
      @ModelAttribute CategoryDto.CategoryFilter dto
  ) {
    return categoryService.findAllDw(dto);
  }

  @GetMapping("/{id}")
  public CategoryDto.CategoryResponse findById(
      @PathVariable("id") Long id
  ) {
    return categoryService.find(id);
  }

  @Operation(summary = "메뉴 입력", description = "메뉴 입력입니다.")
  @PostMapping("")
  @ResponseBody
  @ResponseStatus(value = HttpStatus.CREATED)
  public Long create(
      @Parameter(description = "board")
      @RequestBody final CategoryDto.CategoryRequest dto
  ) {
    return categoryService.create(dto);
  }

  @Operation(summary = "메뉴 수정", description = "메뉴 수정입니다.")
  @PutMapping("")
  @ResponseBody
  @ResponseStatus(value = HttpStatus.OK)
  public Long update(
      @RequestBody final CategoryDto.CategoryRequest dto
  ) {
    return categoryService.update(dto);
  }

  @Operation(summary = "메뉴 삭제", description = "메뉴 삭제입니다.")
  @DeleteMapping("/{id}")
  @ResponseStatus(value = HttpStatus.OK)
  public void delete(
      @PathVariable("id") Long id
  ) {
    categoryService.delete(id);
  }
}
