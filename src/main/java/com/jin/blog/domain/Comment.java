package com.jin.blog.domain;

import com.jin.blog.dto.CommentDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class Comment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long parentId;
  private Long boardId;
  private String content;
  private String writer;
  private LocalDateTime createdDate;
  private LocalDateTime updatedDate;
  @OneToMany(mappedBy = "parentId", fetch = FetchType.LAZY)
  private List<Comment> children = new ArrayList<>();

  public static CommentDto.CommentResponse toDto(Comment comment) {
    return CommentDto.CommentResponse.builder().comment(comment).build();
  }

  public static CommentDto.CommentDwResponse toDtoDw(Comment comment) {
    return CommentDto.CommentDwResponse.builder().comment(comment).build();
  }

  public void update(CommentDto.CommentRequest dto) {
    this.content = dto.getContent();
    this.updatedDate = LocalDateTime.now();
  }
}
