package com.jin.blog.repository;

import com.jin.blog.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

  public List<Comment> findAllByParentIdIsNullAndBoardId(Long boardId);
}
