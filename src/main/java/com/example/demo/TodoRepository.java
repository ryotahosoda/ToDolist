package com.example.demo;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findAllByOrderByMakedateDesc();
    List<Todo> findByNameLikeAndFinishOrderByMakedateDesc(String name, Boolean finish);
    List<Todo> findByNameLikeOrderByMakedateDesc(String name);
    List<Todo> findByNameOrderByMakedateDesc(String name);
}
