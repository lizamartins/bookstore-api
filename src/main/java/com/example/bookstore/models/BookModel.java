package com.example.bookstore.models;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Entity
@Table(name="books")
@Data
public class BookModel extends RepresentationModel<BookModel> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private UUID id;
	private String title;
	private String author;
	private String publicationDate;
	private String synopsis;
}
