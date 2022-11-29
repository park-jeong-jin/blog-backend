package com.jin.blog.service;

import com.jin.blog.domain.Board;
import com.jin.blog.domain.Comment;
import com.jin.blog.dto.CommentDto;
import com.jin.blog.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CommentService {

  @Autowired
  CommentRepository commentRepository;
  @Autowired
  BoardService boardService;

  public Comment findById(Long id) {
    Optional<Comment> comment = commentRepository.findById(id);
    comment.orElseThrow(() -> new EntityNotFoundException("EntityNotFound"));
    return comment.get();
  }

  public List<CommentDto.CommentResponse> findAll(CommentDto.CommentFilter dto) {
    List<Comment> comments = commentRepository.findAllByParentIdIsNullAndBoardId(dto.getBoardId());
    return comments.stream().map(Comment::toDto).collect(Collectors.toList());
  }

  public List<CommentDto.CommentDwResponse> findAllDw(CommentDto.CommentFilter dto) {
    List<Comment> comments = commentRepository.findAllByParentIdIsNullAndBoardId(dto.getBoardId());
    return comments.stream().map(Comment::toDtoDw).collect(Collectors.toList());
  }

  public Long create(CommentDto.CommentRequest dto) {
    Comment comment = dto.toEntity();
    Board board = boardService.findById(dto.getBoardId());
    comment.setBoard(board);
    comment.setCreatedDate(LocalDateTime.now());
    commentRepository.save(comment);
    return comment.getId();
  }

  public Long update(CommentDto.CommentRequest dto) {
    Comment comment = findById(dto.getId());
    comment.update(dto);
    return comment.getId();
  }

  public void delete(Long id) {
    Comment comment = findById(id);
    commentRepository.delete(comment);
  }
}
