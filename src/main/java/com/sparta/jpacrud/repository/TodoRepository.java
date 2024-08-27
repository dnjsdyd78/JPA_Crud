package com.sparta.jpacrud.repository;

import com.sparta.jpacrud.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> {


    Optional<Todo> findByUsername(String name);
}
