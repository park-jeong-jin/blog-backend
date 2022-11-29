package com.jin.blog.domain;

import com.jin.blog.dto.CommentDto;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class Comment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long parentId;
  private String content;
  private String writer;
  @CreationTimestamp
  private LocalDateTime createdDate;
  @UpdateTimestamp
  private LocalDateTime updatedDate;
  @OneToMany(mappedBy = "parentId", fetch = FetchType.LAZY)
  private List<Comment> children = new ArrayList<>();
  @ManyToOne(targetEntity = Board.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "boardId")
  private Board board;

  public static CommentDto.CommentResponse toDto(Comment comment) {
    return CommentDto.CommentResponse.builder().comment(comment).build();
  }

  public static CommentDto.CommentDwResponse toDtoDw(Comment comment) {
    return CommentDto.CommentDwResponse.builder().comment(comment).build();
  }

  public void update(CommentDto.CommentRequest dto) {
    this.content = dto.getContent();
  }

  private CommentDto.CommentDwResponse getCommentDtoDw() {
    return CommentDto.CommentDwResponse.builder().comment(this).build();
  }
}
