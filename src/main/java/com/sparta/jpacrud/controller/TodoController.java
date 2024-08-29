package com.sparta.jpacrud.controller;


import com.sparta.jpacrud.dto.PageDto;
import com.sparta.jpacrud.dto.TodoRequestDto;
import com.sparta.jpacrud.dto.TodoResponseDto;
import com.sparta.jpacrud.service.TodoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }
    //일정생성
    @PostMapping("/todo")
    public TodoResponseDto createTodo(@RequestBody TodoRequestDto requestDto) {
        return todoService.createTodo(requestDto);
    }

    //일정조회
    @GetMapping("/todo/{name}")
    public TodoResponseDto getTodo(@PathVariable String name) {
        return todoService.getTodo(name);
    }

    //일정페이지 조회
    @GetMapping("/todo/list")
    public List<PageDto> getTodoList(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return todoService.getTodos(page,size);
    }

    //일정수정
    @PutMapping("/todo/{id}")
    public TodoResponseDto updateTodo(@PathVariable Long id, @RequestBody TodoRequestDto requestDto) {
        return todoService.updateTodo(id, requestDto);
    }

    //일정삭제
    @DeleteMapping("/todo/{id}")
    public String deleteTodo(@PathVariable Long id) {
        return todoService.deleteTodo(id);
    }
}
