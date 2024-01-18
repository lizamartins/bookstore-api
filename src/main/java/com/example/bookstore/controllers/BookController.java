package com.example.bookstore.controllers;

import com.example.bookstore.models.BookModel;
import com.example.bookstore.services.BookService;
import com.example.bookstore.dtos.BookRecordDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.UUID;

@RestController
@RequestMapping("/books")
public class BookController {

	@Autowired
    BookService bookService;

	@PostMapping
	public ResponseEntity<BookModel> saveBook(@RequestBody BookRecordDto bookRecordDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(bookService.saveBook(bookRecordDto));
	}

	@GetMapping
	public ResponseEntity<Page<BookModel>> getAllBooks(Pageable pageable) {
		return ResponseEntity.status(HttpStatus.OK).body(bookService.getAllBooks(pageable));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getOneBook(@PathVariable(value="id") UUID id) {
		var response = bookService.getOneBook(id);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> updateBook(@PathVariable(value="id") UUID id, @RequestBody @Valid BookRecordDto bookRecordDto) {
		var response = bookService.updateBook(id, bookRecordDto);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteBook(@PathVariable(value="id") UUID id) {
		return ResponseEntity.status(HttpStatus.OK).body(bookService.deleteBook(id));
	}
}

