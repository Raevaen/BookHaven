package com.example.bookHaven.controller;

import com.example.bookHaven.entity.Book;
import com.example.bookHaven.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private IBookService bookService;

    @PostMapping("/create")
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        try {
            Book createdBook = bookService.create(book);
            return ResponseEntity.ok(createdBook);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Book> updateBook(@RequestBody Book book) {
        try {
            Book updatedBook = bookService.update(book);
            return ResponseEntity.ok(updatedBook);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Book> findById(@PathVariable String id) {
        Book book = bookService.findById(id);
        if (book != null) {
            return ResponseEntity.ok(book);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchBooks(@RequestParam String title, @RequestParam String genre, @RequestParam String author) {
        List<Book> books = bookService.searchBooks(title, genre, author);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/existsById/{id}")
    public ResponseEntity<Boolean> existsById(@PathVariable String id) {
        boolean exists = bookService.existsById(id);
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/existsByTitle/{title}")
    public ResponseEntity<Boolean> existsByTitle(@PathVariable String title) {
        boolean exists = bookService.existsByTitle(title);
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/existsByAuthor/{author}")
    public ResponseEntity<Boolean> existsByAuthor(@PathVariable String author) {
        boolean exists = bookService.existsByAuthor(author);
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/existsByGenre/{genre}")
    public ResponseEntity<Boolean> existsByGenre(@PathVariable String genre) {
        boolean exists = bookService.existsByGenre(genre);
        return ResponseEntity.ok(exists);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        boolean isDeleted = bookService.deleteById(id);
        return isDeleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deleteByTitle/{title}")
    public ResponseEntity<Void> deleteByTitle(@PathVariable String title) {
        boolean isDeleted = bookService.deleteByTitle(title);
        return isDeleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deleteByGenre/{genre}")
    public ResponseEntity<Void> deleteByGenre(@PathVariable String genre) {
        boolean isDeleted = bookService.deleteByGenre(genre);
        return isDeleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deleteByAuthor/{author}")
    public ResponseEntity<Void> deleteByAuthor(@PathVariable String author) {
        boolean isDeleted = bookService.deleteByAuthor(author);
        return isDeleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countBooks() {
        long count = bookService.count();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/listAll")
    public ResponseEntity<List<Book>> listAllBooks() {
        List<Book> books = bookService.listAll();
        return ResponseEntity.ok(books);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }

}
