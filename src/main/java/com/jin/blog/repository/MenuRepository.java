package com.jin.blog.repository;

import com.jin.blog.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

  public Optional<Menu> findById(Long id);

  public List<Menu> findAllByNameLikeOrderBySequence(String name);

  public List<Menu> findAllByParentIdIsNullAndNameLikeOrderBySequence(String name);
}
