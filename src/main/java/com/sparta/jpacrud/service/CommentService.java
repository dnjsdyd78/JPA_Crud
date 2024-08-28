package com.sparta.jpacrud.service;

import com.sparta.jpacrud.dto.CommentRequestDto;
import com.sparta.jpacrud.entity.Comment;
import com.sparta.jpacrud.entity.Todo;
import com.sparta.jpacrud.repository.CommentRepository;
import com.sparta.jpacrud.repository.TodoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final TodoRepository todoRepository;
    private final TodoService todoService;

    public CommentService(CommentRepository commentRepository, TodoRepository todoRepository, TodoService todoService) {
        this.commentRepository = commentRepository;
        this.todoRepository = todoRepository;
        this.todoService = todoService;
    }


    public Todo addComment(Long id, CommentRequestDto requestDto) {
        Todo todo = todoService.findTodo(id);
        Comment comment = new Comment(requestDto);
        todo.addComment(comment);
        commentRepository.save(comment);

        //댓글 생성후 댓글달린 일정정보 리턴
        return todo;
    }

    public List<Comment> getComments(String name) {

        List<Comment> comments = commentRepository.findByAuthor(name).orElseThrow(
                () -> new IllegalArgumentException("조회되는 댓글이 없습니다.")
        );
        return comments;

    }

    public List<Comment> getAllComments(){
        return commentRepository.findAll();
    }

    @Transactional
    public Comment updateComment(Long id, CommentRequestDto requestDto) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 댓글입니다."));
        comment.update(requestDto);

        return commentRepository.save(comment);
    }

    public String deleteComment(Long id) {
        if (commentRepository.findById(id).isPresent()){
            commentRepository.deleteById(id);
            return "댓글이 삭제되었습니다.";
        } else {
            return "존재하지 않는 댓글입니다.";
        }
    }
}
