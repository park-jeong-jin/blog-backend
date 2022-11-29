package com.jin.blog.repository;

import com.jin.blog.domain.Board;
import com.jin.blog.domain.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

  public List<Board> findAllByCategory(Category category);

  public Page<Board> findAllByCategory(Category category, Pageable pageable);

  public Optional<Board> findById(Long id);
}
