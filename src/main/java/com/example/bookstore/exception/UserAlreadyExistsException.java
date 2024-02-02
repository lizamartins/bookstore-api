package com.example.bookstore.exception;

import java.io.Serializable;

public class UserAlreadyExistsException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 1L;
}
