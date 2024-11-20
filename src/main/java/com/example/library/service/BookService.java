package com.example.library.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.library.model.Book;
import com.example.library.repository.BookRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public ResponseEntity<Object> addBook(Book book) {
        try {
            Book savedBook = bookRepository.save(book);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error adding book: " + e.getMessage());
        }
    }

    public ResponseEntity<Object> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        if (books.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No books available");
        }
        return ResponseEntity.ok(books);
    }

    public ResponseEntity<Object> updateBook(int id, Book book) {
        Optional<Book> existingBook = bookRepository.findById(id);
        if (existingBook.isPresent()) {
            book.setId(id); 
            Book updatedBook = bookRepository.save(book);
            return ResponseEntity.ok(updatedBook);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Book ID not found");
    }

    public ResponseEntity<Object> deleteBookById(int id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return ResponseEntity.ok("Book deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Book ID not found");
    }
}
