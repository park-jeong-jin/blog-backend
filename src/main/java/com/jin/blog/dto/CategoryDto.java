package com.jin.blog.dto;

import com.jin.blog.domain.Category;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.apache.commons.lang3.ObjectUtils;

import java.util.List;

public class CategoryDto {

  @Getter
  @AllArgsConstructor(access = AccessLevel.PUBLIC)
  public static class CategoryResponse {
    Long id;
    Integer sequence;
    String type;
    String name;
    Long parentId;

    @Builder
    private CategoryResponse(
        Category category
    ) {
      this.id = category.getId();
      this.sequence = category.getSequence();
      this.type = category.getType();
      this.name = category.getName();
      this.parentId = category.getParentId();
    }
  }

  @Getter
  @AllArgsConstructor(access = AccessLevel.PUBLIC)
  public static class CategoryDwResponse {
    Long id;
    Integer sequence;
    String type;
    String name;
    List<Category> children;

    @Builder
    private CategoryDwResponse(
        Category category
    ) {
      this.id = category.getId();
      this.sequence = category.getSequence();
      this.type = category.getType();
      this.name = category.getName();
      this.children = category.getChildren();
    }
  }

  @Getter
  public static class CategoryRequest {
    Long id;
    Integer sequence;
    String type;
    String name;
    Long parentId;

    @Builder
    private CategoryRequest(
        Long id,
        Integer sequence,
        String type,
        String name,
        Long parentId
    ) {
      this.id = id;
      this.sequence = sequence;
      this.type = type;
      this.name = name;
      this.parentId = parentId;
    }

    public Category toEntity() {
      return Category.builder()
          .id(this.id)
          .sequence(this.sequence)
          .type(this.type)
          .name(this.name)
          .parentId(parentId)
          .build();
    }
  }

  @Getter
  public static class CategoryFilter {
    Long id;
    String nameL = "%";

    @Builder
    public CategoryFilter(
        Long id,
        String nameL
    ) {
      this.id = id;
      this.nameL = "%" + (ObjectUtils.isEmpty(nameL) ? "" : nameL) + "%";
    }
  }
}
