package com.example.bookstore.controllers;

import com.example.bookstore.services.UserService;
import com.example.bookstore.dtos.LoginRecordDto;
import com.example.bookstore.dtos.RegisterRecordDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.management.remote.JMXAuthenticator;

@RestController
@RequestMapping("auth")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginRecordDto loginDto) {
        userService.login(loginDto);
        return ResponseEntity.status(HttpStatus.OK).body("login completed");
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterRecordDto registerDto) {
        userService.register(registerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("user registered successfully");
    }
}

