package io.github.pdrotmz.libraryAPI.controller;

import io.github.pdrotmz.libraryAPI.dto.book.BookRequestDTO;
import io.github.pdrotmz.libraryAPI.dto.book.BookResponseDTO;
import io.github.pdrotmz.libraryAPI.model.Author;
import io.github.pdrotmz.libraryAPI.model.Book;
import io.github.pdrotmz.libraryAPI.projection.BookSummaryProjection;
import io.github.pdrotmz.libraryAPI.projection.BookTitleOnly;
import io.github.pdrotmz.libraryAPI.service.BookServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("book")
public class BookController {

    @Autowired
    private BookServiceImpl bookService;

    @PostMapping("/register")
    public ResponseEntity<BookResponseDTO> registerBook(@RequestBody @Valid BookRequestDTO request) {
        BookResponseDTO response = bookService.registerBook(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/list-all")
    public ResponseEntity<List<Book>> findAllBooks() {
        List<Book> books = bookService.findAllBooks();
        return ResponseEntity.status(HttpStatus.OK).body(books);
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
    public ResponseEntity<List<BookTitleOnly>> findBookByTitle(@PathVariable String title) {
        List<BookTitleOnly> books = bookService.findBookByTitle(title);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(books);
    }

    @GetMapping("filter-by/category/{category}")
    public ResponseEntity<List<BookSummaryProjection>> findBooksByCategory(@PathVariable String category) {
        List<BookSummaryProjection> booksCategory = bookService.findBooksByCategory(category);
        return ResponseEntity.status(HttpStatus.OK).body(booksCategory);
    }

    @GetMapping("/filter-by/price-between")
    public ResponseEntity<List<BookSummaryProjection>> findByBookPriceBetween(
            @RequestParam BigDecimal initialPrice,
            @RequestParam BigDecimal finalPrice) {
        List<BookSummaryProjection> bookPrices = bookService.findBookPriceBetween(initialPrice, finalPrice);
        return ResponseEntity.status(HttpStatus.OK).body(bookPrices);
    }

    @GetMapping("search-by/isbn/{isbn}")
    public ResponseEntity<BookTitleOnly> findBookByIsbn(@PathVariable String isbn) {
        Optional<BookTitleOnly> isbnBook = bookService.findBookByIsbn(isbn);
        return isbnBook.map(ResponseEntity::ok).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("filter-by/release-date/{releaseDate}")
    public ResponseEntity<List<BookSummaryProjection>> findBooksByReleaseDate(@PathVariable String releaseDate) {
        List<BookSummaryProjection> books = bookService.findBooksByReleaseDate(releaseDate);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(books);
    }

    @GetMapping("filter-by/author/{authorId}")
    public ResponseEntity<List<BookSummaryProjection>> findBooksByAuthorId(@PathVariable UUID authorId) {
        List<BookSummaryProjection> books = bookService.findBooksByAuthorId(authorId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(books);
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
