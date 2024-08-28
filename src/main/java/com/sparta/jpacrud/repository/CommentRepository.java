package com.sparta.jpacrud.repository;


import com.sparta.jpacrud.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<List<Comment>> findByAuthor(String name);
}
