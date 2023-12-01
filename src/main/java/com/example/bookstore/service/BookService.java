package com.example.bookstore.service;

import com.example.bookstore.controller.BookController;
import com.example.bookstore.dto.BookRecordDto;
import com.example.bookstore.model.BookModel;
import com.example.bookstore.repository.BookRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public BookModel saveBook(@RequestBody @Valid BookRecordDto bookRecordDto) {
        var bookModel = new BookModel();
        BeanUtils.copyProperties(bookRecordDto, bookModel);
        bookRepository.save(bookModel);
        return bookModel;
    }

    public List<BookModel> getAllBooks() {
        List<BookModel> bookList = bookRepository.findAll();
        if(!bookList.isEmpty()) {
            for(BookModel book : bookList) {
                UUID id = book.getId();
                book.add(linkTo(methodOn(BookController.class).getOneBook(id)).withSelfRel());
            }
        }
        return bookList;
    }

    public Object getOneBook(UUID id) {
        Optional<BookModel> book = bookRepository.findById(id);
        if(book.isEmpty()) {
            return null;
        }
        return book.get();
    }
    public Object updateBook(UUID id, BookRecordDto bookRecordDto) {
        Optional<BookModel> book = bookRepository.findById(id);
        if(book.isEmpty()) {
            return "Book not found";
        }
        var booksModel = book.get();
        BeanUtils.copyProperties(bookRecordDto, booksModel);
        return bookRepository.save(booksModel);
    }
    public Object deleteBook(@PathVariable(value="id") UUID id) {
        Optional<BookModel> book = bookRepository.findById(id);
        if(book.isEmpty()) {
            return null;
        }
        bookRepository.delete(book.get());
        return "Book deleted";
    }

}
