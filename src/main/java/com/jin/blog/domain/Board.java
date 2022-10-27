package com.jin.blog.domain;

import com.jin.blog.dto.BoardDto;
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
public class Board {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long categoryId;
  private String title;
  private String content;
  private String writer;
  private Integer hits;
  private LocalDateTime createdDate = LocalDateTime.now();
  private LocalDateTime updatedDate = LocalDateTime.now();
  @OneToMany(targetEntity = Comment.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "boardId")
  private List<Comment> comments = new ArrayList<>();

  public static BoardDto.BoardResponse toDto(Board board) {
    return BoardDto.BoardResponse.builder().board(board).build();
  }

  public static BoardDto.BoardDwResponse toDtoDw(Board board) {
    return BoardDto.BoardDwResponse.builderDw().board(board).build();
  }

  public void update(BoardDto.BoardRequest dto) {
    this.title = dto.getTitle();
    this.content = dto.getContent();
    this.writer = dto.getWriter();
    this.updatedDate = LocalDateTime.now();
  }
}
