package com.example.bookstore.exceptions;

import java.io.Serializable;

public class BookNotFoundException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 1L;
}