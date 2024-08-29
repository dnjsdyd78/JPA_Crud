package com.sparta.jpacrud.repository;

import com.sparta.jpacrud.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
