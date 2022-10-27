package com.jin.blog.controller;

import com.jin.blog.dto.CommentDto;
import com.jin.blog.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "댓글", description = "댓글 관련 api 입니다.")
@RestController
@RequestMapping("/comment")
public class CommentController {

  @Autowired
  CommentService commentService;

  @Operation(summary = "댓글 조회", description = "댓글 조회입니다.")
  @GetMapping("")
  public List<CommentDto.CommentResponse> findAll(
      @ModelAttribute CommentDto.CommentFilter dto
  ) {
    return commentService.findAll(dto);
  }

  @Operation(summary = "댓글 조회", description = "댓글 조회입니다.")
  @GetMapping("/dw")
  public List<CommentDto.CommentDwResponse> findAllDw(
      @ModelAttribute CommentDto.CommentFilter dto
  ) {
    return commentService.findAllDw(dto);
  }

  @Operation(summary = "댓글 입력", description = "댓글 입력입니다.")
  @PostMapping("")
  @ResponseBody
  @ResponseStatus(value = HttpStatus.CREATED)
  public Long create(
      @Parameter(description = "board")
      @RequestBody final CommentDto.CommentRequest dto
  ) {
    return commentService.create(dto);
  }

  @Operation(summary = "댓글 수정", description = "댓글 수정입니다.")
  @PutMapping("")
  @ResponseBody
  @ResponseStatus(value = HttpStatus.OK)
  public Long update(
      @RequestBody final CommentDto.CommentRequest dto
  ) {
    return commentService.update(dto);
  }

  @Operation(summary = "댓글 삭제", description = "댓글 삭제입니다.")
  @DeleteMapping("/{id}")
  @ResponseStatus(value = HttpStatus.OK)
  public void delete(
      @PathVariable("id") Long id
  ) {
    commentService.delete(id);
  }

}
