package com.jin.blog.dto;

import com.jin.blog.domain.Menu;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.apache.commons.lang3.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

public class MenuDto {

  @Getter
  @AllArgsConstructor(access = AccessLevel.PUBLIC)
  public static class MenuResponse {
    Long id;
    Integer sequence;
    String type;
    String name;
    Long parentId;

    @Builder
    private MenuResponse(
        Menu menu
    ) {
      this.id = menu.getId();
      this.sequence = menu.getSequence();
      this.type = menu.getType();
      this.name = menu.getName();
      this.parentId = menu.getParentId();
    }
  }

  @Getter
  @AllArgsConstructor(access = AccessLevel.PUBLIC)
  public static class MenuDwResponse {
    Long id;
    Integer sequence;
    String type;
    String name;
    List<MenuDwResponse> children;

    @Builder
    private MenuDwResponse(
        Menu menu
    ) {
      this.id = menu.getId();
      this.sequence = menu.getSequence();
      this.type = menu.getType();
      this.name = menu.getName();
      this.children = menu.getChildren().stream().map(child -> MenuDwResponse.builder().menu(child).build()).collect(Collectors.toList());
    }
  }

  @Getter
  public static class MenuRequest {
    Long id;
    Integer sequence;
    String type;
    String name;
    Long parentId;

    @Builder
    private MenuRequest(
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

    public Menu toEntity() {
      return Menu.builder()
          .id(this.id)
          .sequence(this.sequence)
          .type(this.type)
          .name(this.name)
          .parentId(parentId)
          .build();
    }
  }

  @Getter
  public static class MenuFilter {
    Long id;
    String nameL = "%";

    @Builder
    public MenuFilter(
        Long id,
        String nameL
    ) {
      this.id = id;
      this.nameL = "%" + (ObjectUtils.isEmpty(nameL) ? "" : nameL) + "%";
    }
  }
}
