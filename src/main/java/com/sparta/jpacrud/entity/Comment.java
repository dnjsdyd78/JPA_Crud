package com.sparta.jpacrud.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sparta.jpacrud.dto.CommentRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "comment")
@NoArgsConstructor

public class Comment extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "message")
    private String message;
    @Column(name = "author")
    private String author;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "todo_id")
    @JsonBackReference
    private Todo todo;

    public Comment(CommentRequestDto requestDto) {
        this.message = requestDto.getMessage();
        this.author = requestDto.getAuthor();
    }

    public Comment update(CommentRequestDto requestDto){
        this.author = requestDto.getAuthor();
        this.message = requestDto.getMessage();
        return this;
    }
}
