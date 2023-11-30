package com.example.bookstore.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bookstore.model.BookModel;

@Repository
public interface BookRepository extends JpaRepository<BookModel, UUID>{

}