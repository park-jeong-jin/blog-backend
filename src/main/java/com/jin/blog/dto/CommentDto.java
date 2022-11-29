package com.jin.blog.dto;

import com.jin.blog.domain.Comment;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class CommentDto {

  @Getter
  @AllArgsConstructor(access = AccessLevel.PUBLIC)
  public static class CommentResponse {
    Long id;
    Long parentId;
    String content;
    String writer;
    LocalDateTime createdDate;
    LocalDateTime updatedDate;

    @Builder
    private CommentResponse(
        Comment comment
    ) {
      this.id = comment.getId();
      this.parentId = comment.getParentId();
      this.content = comment.getContent();
      this.writer = comment.getWriter();
      this.createdDate = comment.getCreatedDate();
      this.updatedDate = comment.getUpdatedDate();
    }
  }

  @Getter
  @AllArgsConstructor(access = AccessLevel.PUBLIC)
  public static class CommentDwResponse {
    Long id;
    String content;
    String writer;
    LocalDateTime createdDate;
    LocalDateTime updatedDate;
    List<CommentDwResponse> children;

    @Builder
    private CommentDwResponse(
        Comment comment
    ) {
      this.id = comment.getId();
      this.content = comment.getContent();
      this.writer = comment.getWriter();
      this.children = comment.getChildren().stream().map(child -> CommentDwResponse.builder().comment(child).build()).collect(Collectors.toList());
    }
  }

  @Getter
  public static class CommentRequest {
    Long id;
    Long parentId;
    Long boardId;
    String content;
    String writer;

    @Builder
    private CommentRequest(
        Long id,
        Long parentId,
        Long boardId,
        String content,
        String writer
    ) {
      this.id = id;
      this.parentId = parentId;
      this.boardId = boardId;
      this.content = content;
      this.writer = writer;
    }

    public Comment toEntity() {
      return Comment.builder()
          .id(this.id)
          .parentId(this.parentId)
          .content(this.content)
          .writer(this.writer)
          .build();
    }
  }

  @Getter
  public static class CommentFilter {
    Long id;
    Long parentId;
    Long boardId;
    String writer;

    @Builder
    public CommentFilter(
        Long id,
        Long parentId,
        Long boardId,
        String writer
    ) {
      this.id = id;
      this.parentId = parentId;
      this.boardId = boardId;
      this.writer = writer;
    }
  }
}
