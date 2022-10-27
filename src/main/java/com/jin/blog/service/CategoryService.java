package com.jin.blog.service;

import com.jin.blog.domain.Category;
import com.jin.blog.dto.CategoryDto;
import com.jin.blog.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoryService {

  @Autowired
  CategoryRepository boardRepository;

  public Category findById(Long id) {
    Optional<Category> board = boardRepository.findById(id);
    board.orElseThrow(() -> new EntityNotFoundException("EntityNotFound"));
    return board.get();
  }

  public CategoryDto.CategoryResponse find(Long id) {
    Category board = findById(id);
    return Category.toDto(board);
  }

  public List<CategoryDto.CategoryResponse> findAll(CategoryDto.CategoryFilter dto) {
    List<Category> boards = boardRepository.findAllByNameLikeOrderBySequence(dto.getNameL());
    return boards.stream().map(Category::toDto).collect(Collectors.toList());
  }

  public List<CategoryDto.CategoryDwResponse> findAllDw(CategoryDto.CategoryFilter dto) {
    List<Category> boards = boardRepository.findAllByParentIdIsNullAndNameLikeOrderBySequence(dto.getNameL());
    return boards.stream().map(Category::toDtoDw).collect(Collectors.toList());
  }

  public Long create(CategoryDto.CategoryRequest dto) {
    Category board = dto.toEntity();
    boardRepository.save(board);
    return board.getId();
  }

  public Long update(CategoryDto.CategoryRequest dto) {
    Category board = findById(dto.getId());
    board.update(dto);
    return board.getId();
  }

  public void delete(Long id) {
    Category board = findById(id);
    boardRepository.delete(board);
  }
}
