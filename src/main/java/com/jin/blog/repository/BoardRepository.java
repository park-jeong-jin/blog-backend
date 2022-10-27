package com.jin.blog.repository;

import com.jin.blog.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

  public List<Board> findAllByCategoryId(Long categoryId);

  public Page<Board> findAllByCategoryId(Long categoryId, Pageable pageable);

  public Optional<Board> findById(Long id);
}
