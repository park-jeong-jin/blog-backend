package com.jin.blog.domain;

import com.jin.blog.dto.BoardDto;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.hibernate.annotations.WhereJoinTable;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class Board {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;
  private String content;
  private String writer;
  @CreationTimestamp
  private LocalDateTime createdDate;
  @UpdateTimestamp
  private LocalDateTime updatedDate;
  @ManyToOne(targetEntity = Category.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "categoryId")
  private Category category;
  @OneToMany(targetEntity = Comment.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "boardId")
  @Where(clause = "parent_id is null")
  private List<Comment> comments;

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
  }
}
