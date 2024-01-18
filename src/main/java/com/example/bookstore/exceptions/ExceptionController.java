package com.example.bookstore.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<String> handle(BookNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found");
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<String> handle(UserAlreadyExistsException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists");
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handle(UserNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("User not found");
    }

}
