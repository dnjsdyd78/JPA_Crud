package com.sparta.jpacrud.dto;

import com.sparta.jpacrud.entity.Comment;

public class CommentResponseDto {
    private String author;
    private String contents;

    public CommentResponseDto(Comment comment) {
        this.author = comment.getAuthor();
        this.contents = comment.getMessage();
    }
}
