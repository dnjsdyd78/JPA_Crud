package com.sparta.jpacrud.service;

import com.sparta.jpacrud.dto.PageDto;
import com.sparta.jpacrud.dto.TodoRequestDto;
import com.sparta.jpacrud.dto.TodoResponseDto;
import com.sparta.jpacrud.dto.UserRequestDto;
import com.sparta.jpacrud.entity.Todo;
import com.sparta.jpacrud.entity.User;
import com.sparta.jpacrud.repository.TodoRepository;
import com.sparta.jpacrud.repository.UserRepository;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service

public class TodoService {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    public TodoService(TodoRepository todoRepository, EntityManager em, UserRepository userRepository) {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
    }

    public TodoResponseDto createTodo(TodoRequestDto todoRequestDto) {
        User user = new User(todoRequestDto);
        Todo todo = new Todo(todoRequestDto);
        todo.setUser(user);

        userRepository.save(user);
        Todo saveTodo = todoRepository.save(todo);
        return new TodoResponseDto(saveTodo);
    }


    public TodoResponseDto updateTodo(Long id, TodoRequestDto requestDto) {
        Todo todo = findTodo(id);
        todo.update(requestDto);
        todoRepository.save(todo);
        return new TodoResponseDto(todo);
    }


    public TodoResponseDto getTodo(String name) {
        Todo todo = todoRepository.findByUsername(name).orElseThrow(() ->
                new IllegalArgumentException("일치하는 담당자가 없습니다."));

        return new TodoResponseDto(todo);
    }

    public List<PageDto> getTodos(int page, int size) {
        //정렬옵션값 정의
        Sort sort = Sort.by(Sort.Direction.DESC, "updateAt");
        //페이지 옵션 주입
        Pageable pageable = PageRequest.of(page,size,sort);
        //페이지네이션 적용
        Page<Todo> todoPage = todoRepository.findAll(pageable);

        // 출력해야하는 속성만 추출하여 객체리스트생성
        List<PageDto> PageList = todoPage.stream().
                map(todo -> new PageDto(todo)).collect(Collectors.toList());

        return PageList;
    }

    public String deleteTodo(Long id) {
        if (todoRepository.findById(id).isPresent()) {
            todoRepository.deleteById(id);
            return "일정이 삭제되었습니다.";
        } else return "존재하지 않는 일정입니다.";
    }

    public Todo findTodo(Long id) {
        return todoRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("존재하지 않는 일정입니다."));
    }
}
