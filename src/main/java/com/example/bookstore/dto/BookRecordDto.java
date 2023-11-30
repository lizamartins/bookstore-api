package com.example.bookstore.dto;

import jakarta.validation.constraints.NotBlank;

public record BookRecordDto(@NotBlank String title, @NotBlank String author, @NotBlank String publicationDate, @NotBlank String synopsis) {

}
