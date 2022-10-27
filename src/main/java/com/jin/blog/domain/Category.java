package com.jin.blog.domain;

import com.jin.blog.dto.CategoryDto;
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
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Integer sequence;
  private String type;
  private String name;
  private Long parentId;
  @OneToMany(mappedBy = "parentId", fetch = FetchType.LAZY)
  private List<Category> children = new ArrayList<>();

  public static CategoryDto.CategoryResponse toDto(Category category) {
    return CategoryDto.CategoryResponse.builder().category(category).build();
  }

  public static CategoryDto.CategoryDwResponse toDtoDw(Category category) {
    return CategoryDto.CategoryDwResponse.builder().category(category).build();
  }

  public void update(CategoryDto.CategoryRequest dto) {
  }
}
