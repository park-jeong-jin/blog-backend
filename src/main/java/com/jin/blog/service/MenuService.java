package com.jin.blog.service;

import com.jin.blog.domain.Menu;
import com.jin.blog.dto.MenuDto;
import com.jin.blog.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class MenuService {

  @Autowired
  MenuRepository boardRepository;

  public Menu findById(Long id) {
    Optional<Menu> board = boardRepository.findById(id);
    board.orElseThrow(() -> new EntityNotFoundException("EntityNotFound"));
    return board.get();
  }

  public MenuDto.MenuResponse find(Long id) {
    Menu board = findById(id);
    return Menu.toDto(board);
  }

  public List<MenuDto.MenuResponse> findAll(MenuDto.MenuFilter dto) {
    List<Menu> boards = boardRepository.findAllByNameLikeOrderBySequence(dto.getNameL());
    return boards.stream().map(Menu::toDto).collect(Collectors.toList());
  }

  public List<MenuDto.MenuDwResponse> findAllDw(MenuDto.MenuFilter dto) {
    List<Menu> boards = boardRepository.findAllByParentIdIsNullAndNameLikeOrderBySequence(dto.getNameL());
    return boards.stream().map(Menu::toDtoDw).collect(Collectors.toList());
  }

  public Long create(MenuDto.MenuRequest dto) {
    Menu board = dto.toEntity();
    boardRepository.save(board);
    return board.getId();
  }

  public Long update(MenuDto.MenuRequest dto) {
    Menu board = findById(dto.getId());
    board.update(dto);
    return board.getId();
  }

  public void delete(Long id) {
    Menu board = findById(id);
    boardRepository.delete(board);
  }
}
