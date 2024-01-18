package com.example.bookstore.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bookstore.models.BookModel;

@Repository
public interface BookRepository extends JpaRepository<BookModel, UUID>{

}