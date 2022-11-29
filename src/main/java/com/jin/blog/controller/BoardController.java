package com.jin.blog.controller;

import com.jin.blog.dto.BoardDto;
import com.jin.blog.service.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "게시글", description = "게시글 관련 api 입니다.")
@RestController
@RequestMapping("/board")
public class BoardController {

  @Autowired
  BoardService boardService;

  @Operation(summary = "게시글 조회", description = "게시글 조회입니다.")
  @GetMapping("")
  public List<BoardDto.BoardResponse> findAll(
      @ModelAttribute BoardDto.BoardFilter dto
  ) {
    return boardService.findAll(dto);
  }

  @Operation(summary = "게시글 조회", description = "게시글 조회입니다.")
  @GetMapping("/p")
  public Page<BoardDto.BoardResponse> findAllPaging(
      @ModelAttribute BoardDto.BoardFilter dto,
      @PageableDefault(direction = Sort.Direction.DESC, sort = "createdDate") final Pageable pageable
  ) {
    return boardService.findAllPaging(dto, pageable);
  }

  @GetMapping("/{id}")
  public BoardDto.BoardDwResponse findById(
      @PathVariable("id") Long id
  ) {
    return boardService.findDw(id);
  }

  @Operation(summary = "게시글 입력", description = "게시글 입력입니다.")
  @PostMapping("")
  @ResponseBody
  @ResponseStatus(value = HttpStatus.CREATED)
  public Long create(
      @Parameter(description = "board")
      @RequestBody final BoardDto.BoardRequest dto
  ) {
    return boardService.create(dto);
  }

  @Operation(summary = "게시글 수정", description = "게시글 수정입니다.")
  @PutMapping("")
  @ResponseBody
  @ResponseStatus(value = HttpStatus.OK)
  public Long update(
      @RequestBody final BoardDto.BoardRequest dto
  ) {
    return boardService.update(dto);
  }

  @Operation(summary = "게시글 삭제", description = "게시글 삭제입니다.")
  @DeleteMapping("/{id}")
  @ResponseStatus(value = HttpStatus.OK)
  public void delete(
      @PathVariable("id") Long id
  ) {
    boardService.delete(id);
  }

}
