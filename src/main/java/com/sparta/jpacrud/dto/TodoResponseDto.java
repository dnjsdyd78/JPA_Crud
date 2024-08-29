package com.sparta.jpacrud.dto;

import com.sparta.jpacrud.entity.Todo;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
public class TodoResponseDto {
    private String title;
    private String contents;
    private String userName;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public TodoResponseDto(Todo todo) {
        this.title = todo.getTitle();
        this.contents = todo.getContents();
        this.userName = todo.getUsername();
        this.email = todo.getUser().getEmail();
        this.createdAt = todo.getCreateAt();
        this.updatedAt = todo.getUpdateAt();

    }
}
