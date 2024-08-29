package com.sparta.jpacrud.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sparta.jpacrud.dto.TodoRequestDto;
import com.sparta.jpacrud.dto.UserRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Table(name = "user")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "user")
    @JsonBackReference
    private List<Todo> todos;

    public User(TodoRequestDto requestDto) {
        this.name = requestDto.getUserName();
        this.email = requestDto.getEmail();
    }
}

