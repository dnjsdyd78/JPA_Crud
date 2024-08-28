package com.sparta.jpacrud.controller;

import com.sparta.jpacrud.dto.CommentRequestDto;
import com.sparta.jpacrud.entity.Comment;
import com.sparta.jpacrud.entity.Todo;
import com.sparta.jpacrud.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentsService) {
        this.commentService = commentsService;
    }

    //댓글작성

    @PostMapping("/comment/{id}")
    public Todo addComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto) {

        return commentService.addComment(id, requestDto);
    }

    //댓글조회(특정유저)
    @GetMapping("/comment/{name}")
    public List<Comment> getComments(@PathVariable String name) {
        return commentService.getComments(name);
    }

    //댓글 전체조회
    @GetMapping("/comment/list")
    public List<Comment> getAllComment(){
        return commentService.getAllComments();
    }

    //댓글수정
    @PutMapping("/comment/{id}")
    public Comment updateComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto){
        return commentService.updateComment(id, requestDto);
    }

    //댓글삭제
    @DeleteMapping("/comment/{id}")
    public String deleteComment(@PathVariable Long id){
        return commentService.deleteComment(id);
    }
}
