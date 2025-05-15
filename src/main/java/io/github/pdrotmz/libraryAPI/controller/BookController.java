package io.github.pdrotmz.libraryAPI.controller;

import io.github.pdrotmz.libraryAPI.model.Book;
import io.github.pdrotmz.libraryAPI.projection.BookSummaryProjection;
import io.github.pdrotmz.libraryAPI.projection.BookTitleOnly;
import io.github.pdrotmz.libraryAPI.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("book")
public class BookController {

    @Autowired
    private BookServiceImpl bookService;

    @PostMapping("/register")
    public ResponseEntity<Book> registerBook(@RequestBody Book book) {
        Book newBook = bookService.registerBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(newBook);
    }

    @GetMapping("/search-by/id/{id}")
    public ResponseEntity<BookSummaryProjection> findBookById(@PathVariable UUID id) {
        BookSummaryProjection bookId = bookService.findBookById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(bookId);
    }

    @GetMapping("/search-by/title/{title}")
    public ResponseEntity<BookTitleOnly> findBookByTitle(@PathVariable String title) {
        BookTitleOnly bookTitle = bookService.findBookByTitle(title);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(bookTitle);
    }

    @PutMapping("/update-by/id/{id}")
    public ResponseEntity<Void> updateBookById(@RequestBody Book book, @PathVariable UUID id) {
        bookService.updateBookById(book, id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/delete-by/id/{id}")
    public ResponseEntity<Void> deleteBookById(@PathVariable UUID id) {
        bookService.deleteBookById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
