package io.github.pdrotmz.libraryAPI.controller;

import io.github.pdrotmz.libraryAPI.model.Book;
import io.github.pdrotmz.libraryAPI.projection.BookSummaryProjection;
import io.github.pdrotmz.libraryAPI.projection.BookTitleOnly;
import io.github.pdrotmz.libraryAPI.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
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

    @GetMapping("/list-all/home") // TODO: Changes this endpoints for "/home"
    public ResponseEntity<List<BookSummaryProjection>> findAllSumaries() {
        List<BookSummaryProjection> allBooks = bookService.findAllSummaries();
        return ResponseEntity.status(HttpStatus.OK).body(allBooks);
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

    @GetMapping("/filter-by/price-between")
    public ResponseEntity<List<BookSummaryProjection>> findByBookPriceBetween(
            @RequestParam BigDecimal initialPrice,
            @RequestParam BigDecimal finalPrice) {
        List<BookSummaryProjection> bookPrices = bookService.findBookPriceBetween(initialPrice, finalPrice);
        return ResponseEntity.status(HttpStatus.OK).body(bookPrices);
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
