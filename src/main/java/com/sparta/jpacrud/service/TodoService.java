package com.sparta.jpacrud.service;

import com.sparta.jpacrud.dto.TodoRequestDto;
import com.sparta.jpacrud.dto.TodoResponseDto;
import com.sparta.jpacrud.entity.Todo;
import com.sparta.jpacrud.repository.TodoRepository;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service

public class TodoService {

    private final TodoRepository todoRepository;
    private final EntityManager em;

    public TodoService(TodoRepository todoRepository , EntityManager em) {
        this.todoRepository = todoRepository;
        this.em = em;

    }

    public TodoResponseDto createTodo(TodoRequestDto todoRequestDto) {
        Todo todo = new Todo(todoRequestDto);


        Todo saveTodo = todoRepository.save(todo);

        TodoResponseDto todoResponseDto = new TodoResponseDto(todo);
        return todoResponseDto;
    }

    @Transactional
    public TodoResponseDto updateTodo(Long id, TodoRequestDto requestDto) {
        Todo todo = findTodo(id);
        todo.update(requestDto);

        //바로 반영시켜 클라이언트 반환시 실시간표시
        em.flush();
        return new TodoResponseDto(findTodo(id));
    }


    public TodoResponseDto getTodo(String name) {
        Todo todo = todoRepository.findByUsername(name).orElseThrow(() ->
                new IllegalArgumentException("일치하는 담당자가 없습니다."));

        return new TodoResponseDto(todo);
    }

    public String deleteTodo(Long id) {
        if(todoRepository.findById(id).isPresent()){
            todoRepository.deleteById(id);
            return "일정이 삭제되었습니다.";
        }
        else return "존재하지 않는 일정입니다.";
    }

    private Todo findTodo(Long id) {
        return todoRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("존재하지 않는 일정입니다."));
    }
}
