package com.sparta.jpacrud.controller;


import com.sparta.jpacrud.dto.TodoRequestDto;
import com.sparta.jpacrud.dto.TodoResponseDto;
import com.sparta.jpacrud.entity.Todo;
import com.sparta.jpacrud.service.TodoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping("/todo")
    public TodoResponseDto createTodo(@RequestBody TodoRequestDto requestDto) {
        return todoService.createTodo(requestDto);
    }

    @GetMapping("/todo/{name}")
    public TodoResponseDto getTodo(@PathVariable String name) {
        return todoService.getTodo(name);
    }

    @PutMapping("/todo/{id}")
    public TodoResponseDto updateTodo(@PathVariable Long id, @RequestBody TodoRequestDto requestDto) {
        return todoService.updateTodo(id, requestDto);
    }


    @DeleteMapping("/todo/{id}")
    public String deleteTodo(@PathVariable Long id) {
        return todoService.deleteTodo(id);
    }
}
