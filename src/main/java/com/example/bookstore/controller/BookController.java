package com.example.bookstore.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.bookstore.dto.BookRecordDto;
import com.example.bookstore.model.BookModel;
import com.example.bookstore.repository.BookRepository;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/books")
public class BookController {
	
	@Autowired
	BookRepository bookRepository;
	
	@PostMapping
	public ResponseEntity<BookModel> saveBook(@RequestBody @Valid BookRecordDto bookRecordDto) {
		var booksModel = new BookModel();
		BeanUtils.copyProperties(bookRecordDto, booksModel);
		return ResponseEntity.status(HttpStatus.CREATED).body(bookRepository.save(booksModel));	
	}

	@GetMapping
	public ResponseEntity<List<BookModel>> getAllBooks() {
		List<BookModel> bookList = bookRepository.findAll();
		if(!bookList.isEmpty()) {
			for(BookModel book : bookList) {
				UUID id = book.getId();
				book.add(linkTo(methodOn(BookController.class).getOneBook(id)).withSelfRel());
			}
		}
		return ResponseEntity.status(HttpStatus.OK).body(bookList);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getOneBook(@PathVariable(value="id") UUID id) {
		Optional<BookModel> book = bookRepository.findById(id);
		if(book.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found");
		}
		return ResponseEntity.status(HttpStatus.OK).body(book.get());
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> updateBook(@PathVariable(value="id") UUID id, @RequestBody @Valid BookRecordDto bookRecordDto) {
		Optional<BookModel> book = bookRepository.findById(id);
		if(book.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found");
		}
		var booksModel = book.get();
		BeanUtils.copyProperties(bookRecordDto, booksModel);
		return ResponseEntity.status(HttpStatus.OK).body(bookRepository.save(booksModel));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteBook(@PathVariable(value="id") UUID id) {
		Optional<BookModel> book0 = bookRepository.findById(id);
		if(book0.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found");
		}
		bookRepository.delete(book0.get());
		return ResponseEntity.status(HttpStatus.OK).body("book deleted");
	}
}

