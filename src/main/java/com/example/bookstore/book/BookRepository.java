package com.example.bookstore.book;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bookstore.book.BookModel;

@Repository
public interface BookRepository extends JpaRepository<BookModel, UUID>{

}