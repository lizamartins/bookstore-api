package com.example.bookstore.dtos;

import com.example.bookstore.security.UserRole;

public record RegisterRecordDto(String login, String password, UserRole role) {

}
