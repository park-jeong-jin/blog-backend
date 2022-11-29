package com.jin.blog.service;

import com.jin.blog.domain.Board;
import com.jin.blog.domain.Category;
import com.jin.blog.dto.BoardDto;
import com.jin.blog.dto.CommentDto;
import com.jin.blog.repository.BoardRepository;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class BoardService {

  @Autowired
  BoardRepository boardRepository;
  @Autowired
  CategoryService categoryService;

  public Board findById(Long id) {
    Optional<Board> board = boardRepository.findById(id);
    board.orElseThrow(() -> new EntityNotFoundException("EntityNotFound"));
    return board.get();
  }

  public BoardDto.BoardDwResponse findDw(Long id) {
    Board board = findById(id);
    return Board.toDtoDw(board);
  }

  public List<BoardDto.BoardResponse> findAll(BoardDto.BoardFilter dto) {
    Category category = ObjectUtils.isEmpty(dto.getCategoryId()) ? null : categoryService.findById(dto.getCategoryId());
    List<Board> boards = boardRepository.findAllByCategory(category);
    return boards.stream().map(Board::toDto).collect(Collectors.toList());
  }

  public Page<BoardDto.BoardResponse> findAllPaging(BoardDto.BoardFilter dto, Pageable pageable) {
    Page<Board> boards = ObjectUtils.isEmpty(dto.getCategoryId()) ? boardRepository.findAll(pageable) : boardRepository.findAllByCategory(categoryService.findById(dto.getCategoryId()), pageable);
    return boards.map(Board::toDto);
  }

  public Long create(BoardDto.BoardRequest dto) {
    Board board = dto.toEntity();
    Category category = categoryService.findById(dto.getCategoryId());
    board.setCategory(category);
    board.setCreatedDate(LocalDateTime.now());
    boardRepository.save(board);
    return board.getId();
  }

  public Long update(BoardDto.BoardRequest dto) {
    Board board = findById(dto.getId());
    board.update(dto);
    return board.getId();
  }

  public void delete(Long id) {
    Board board = findById(id);
    boardRepository.delete(board);
  }
}
