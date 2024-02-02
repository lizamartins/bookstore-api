package com.example.bookstore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serializable;

public class BookNotFoundException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 1L;
}
