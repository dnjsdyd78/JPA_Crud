package com.sparta.jpacrud.dto;

import com.sparta.jpacrud.entity.Todo;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PageDto {
    private String title;
    private String contents;
    private int totalComments;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private String userName;

    public PageDto(Todo todo) {
        this.title = todo.getTitle();
        this.contents = todo.getContents();
        this.totalComments = todo.getComments().size();
        this.createAt = todo.getCreateAt();
        this.updateAt = todo.getUpdateAt();
        this.userName = todo.getUsername();
    }
}
