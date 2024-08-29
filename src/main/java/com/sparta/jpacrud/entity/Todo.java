package com.sparta.jpacrud.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sparta.jpacrud.dto.TodoRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "todo")
@NoArgsConstructor
@AllArgsConstructor

public class Todo extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "contents", length = 500)
    private String contents;
    @Column(name = "username")
    private String username;

    @OneToMany(mappedBy = "todo", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Comment> comments = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;


    public Todo(TodoRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.username = requestDto.getUserName();
    }

    public Todo update(TodoRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        return this;
    }

    public void addComment(Comment comment){
        this.comments.add(comment);
        comment.setTodo(this);
    }
}
