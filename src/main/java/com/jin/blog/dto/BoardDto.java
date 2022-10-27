package com.jin.blog.dto;

import com.jin.blog.domain.Board;
import com.jin.blog.domain.Comment;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

public class BoardDto {

  @Getter
  @AllArgsConstructor(access = AccessLevel.PUBLIC)
  public static class BoardResponse {
    Long id;
    Long categoryId;
    String title;
    String content;
    String writer;
    Integer hits;
    LocalDateTime createdDate;
    LocalDateTime updatedDate;

    @Builder
    private BoardResponse(
        Board board
    ) {
      this.id = board.getId();
      this.categoryId = board.getCategoryId();
      this.title = board.getTitle();
      this.content = board.getContent();
      this.writer = board.getWriter();
      this.hits = board.getHits();
      this.createdDate = board.getCreatedDate();
      this.updatedDate = board.getUpdatedDate();
    }
  }

  @Getter
  public static class BoardDwResponse extends BoardResponse {
    List<Comment> comments;

    @Builder(builderMethodName = "builderDw")
    private BoardDwResponse(
        Board board
    ) {
      super(board);
      this.comments = board.getComments();
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
          .hits(0)
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
  }
}
