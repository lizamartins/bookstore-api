package com.example.bookstore.services;

import com.example.bookstore.controllers.BookController;
import com.example.bookstore.dtos.BookRecordDto;
import com.example.bookstore.exceptions.BookNotFoundException;
import com.example.bookstore.models.BookModel;
import com.example.bookstore.repositories.BookRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

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

    public Page<BookModel> getAllBooks(Pageable pageable) {
        Page<BookModel> bookList = bookRepository.findAll(pageable);
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
            throw new BookNotFoundException();
        }
            return book.get();
    }

    public Object updateBook(UUID id, BookRecordDto bookRecordDto) {
        Optional<BookModel> book = bookRepository.findById(id);
        if(book.isEmpty()) {
            throw new BookNotFoundException();
        }
        var booksModel = book.get();
        BeanUtils.copyProperties(bookRecordDto, booksModel);
        return bookRepository.save(booksModel);
    }

    public Object deleteBook(@PathVariable(value="id") UUID id) {
        Optional<BookModel> book = bookRepository.findById(id);
        if(book.isEmpty()) {
            throw new BookNotFoundException();
        }
        bookRepository.delete(book.get());
        return "Book deleted";
    }
}
