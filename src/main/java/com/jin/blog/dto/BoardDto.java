package com.jin.blog.dto;

import com.jin.blog.domain.Board;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class BoardDto {

  @Getter
  @AllArgsConstructor(access = AccessLevel.PUBLIC)
  public static class BoardResponse {
    Long id;
    Long categoryId;
    String title;
    String content;
    String writer;
    LocalDateTime createdDate;
    LocalDateTime updatedDate;

    @Builder
    private BoardResponse(
        Board board
    ) {
      this.id = board.getId();
      // this.categoryId = board.getCategory().getId();
      this.title = board.getTitle();
      this.content = board.getContent();
      this.writer = board.getWriter();
      this.createdDate = board.getCreatedDate();
      this.updatedDate = board.getUpdatedDate();
    }
  }

  @Getter
  public static class BoardDwResponse extends BoardResponse {
    List<CommentDto.CommentDwResponse> comments;

    @Builder(builderMethodName = "builderDw")
    private BoardDwResponse(
        Board board
    ) {
      super(board);
      this.comments = board.getComments().stream().map(comment -> CommentDto.CommentDwResponse.builder().comment(comment).build()).collect(Collectors.toList());
    }
  }

  @Getter
  public static class BoardRequest {
    Long id;
    Long categoryId;
    String title;
    String content;
    String writer;

    @Builder
    private BoardRequest(
        Long id,
        Long categoryId,
        String title,
        String content,
        String writer
    ) {
      this.id = id;
      this.categoryId = categoryId;
      this.title = title;
      this.content = content;
      this.writer = writer;
    }

    public Board toEntity() {
      return Board.builder()
          .id(this.id)
          .title(this.title)
          .content(this.content)
          .writer(writer)
          .build();
    }
  }

  @Getter
  public static class BoardFilter {
    Long id;
    Long categoryId;
    String titleL;
    String contentL;
    String writerL;

    @Builder
    public BoardFilter(
        Long id,
        Long categoryId,
        String titleL,
        String contentL,
        String writerL
    ) {
      this.id = id;
      this.categoryId = categoryId;
      this.titleL = titleL;
      this.contentL = contentL;
      this.writerL = writerL;
    }
  }
}
