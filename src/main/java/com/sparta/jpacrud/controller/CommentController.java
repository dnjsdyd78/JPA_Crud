package com.sparta.jpacrud.controller;

import com.sparta.jpacrud.dto.CommentRequestDto;
import com.sparta.jpacrud.dto.CommentResponseDto;
import com.sparta.jpacrud.entity.Comment;
import com.sparta.jpacrud.entity.Todo;
import com.sparta.jpacrud.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentsService) {
        this.commentService = commentsService;
    }

    @PostMapping("/comment/{id}")
    public Todo addComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto) {
        //댓글 생성후 댓글달린 일정정보 반환
        return commentService.addComment(id, requestDto);
    }

    @GetMapping("/comment/{name}")
    public List<Comment> getCommentById(@PathVariable String name) {
        return commentService.getComments(name);
    }
}
