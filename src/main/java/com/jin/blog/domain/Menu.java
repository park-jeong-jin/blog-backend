package com.jin.blog.domain;

import com.jin.blog.dto.MenuDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class Menu {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Integer sequence;
  private String type;
  private String name;
  private Long parentId;
  @OneToMany(mappedBy = "parentId", fetch = FetchType.LAZY)
  private List<Menu> children = new ArrayList<>();

  public static MenuDto.MenuResponse toDto(Menu menu) {
    return MenuDto.MenuResponse.builder().menu(menu).build();
  }

  public static MenuDto.MenuDwResponse toDtoDw(Menu menu) {
    return MenuDto.MenuDwResponse.builder().menu(menu).build();
  }

  public void update(MenuDto.MenuRequest dto) {
  }
}
