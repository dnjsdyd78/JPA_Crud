package com.sparta.jpacrud.dto;

import lombok.Getter;

@Getter
public class TodoRequestDto {
    private String title;
    private String contents;
    private String userName;
    private String email;
}
