package com.example.bookstore.controller;

import com.example.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.bookstore.dto.BookRecordDto;
import com.example.bookstore.model.BookModel;

import jakarta.validation.Valid;

import java.util.List;
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
	public ResponseEntity<List<BookModel>> getAllBooks() {
		return ResponseEntity.status(HttpStatus.OK).body(bookService.getAllBooks());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getOneBook(@PathVariable(value="id") UUID id) {
		var response = bookService.getOneBook(id);
		if(response == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found");
		}
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> updateBook(@PathVariable(value="id") UUID id, @RequestBody @Valid BookRecordDto bookRecordDto) {
		var response = bookService.updateBook(id, bookRecordDto);
		if(response == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found");
		}
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteBook(@PathVariable(value="id") UUID id) {
		var response = bookService.deleteBook(id);
		if(response == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found");
		}
		return ResponseEntity.status(HttpStatus.OK).body("Book deleted");
	}
}

