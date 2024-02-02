package com.example.bookstore.dtos;

import com.example.bookstore.auth.UserRole;

public record RegisterRecordDto(String login, String password, UserRole role) {

}
