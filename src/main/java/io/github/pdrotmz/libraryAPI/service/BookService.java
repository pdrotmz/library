package io.github.pdrotmz.libraryAPI.service;

import io.github.pdrotmz.libraryAPI.dto.book.BookRequestDTO;
import io.github.pdrotmz.libraryAPI.dto.book.BookResponseDTO;
import io.github.pdrotmz.libraryAPI.model.Book;
import io.github.pdrotmz.libraryAPI.projection.BookSummaryProjection;
import io.github.pdrotmz.libraryAPI.projection.BookTitleOnly;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookService {
    BookResponseDTO registerBook(BookRequestDTO request);
    List<Book> findAllBooks();
    List<BookSummaryProjection> findAllSummaries();
    BookSummaryProjection findBookById(UUID id);
    BookTitleOnly findBookByTitle(String title);
    List<BookSummaryProjection> findBookPriceBetween(BigDecimal initialPrice, BigDecimal finalPrice);
    List<BookSummaryProjection> findBooksByCategory(String category);
    Optional<BookTitleOnly> findBookByIsbn(String isbn);
    List<BookSummaryProjection> findBooksByReleaseDate(String releaseDate);
    void updateBookById(Book book, UUID id);
    void deleteBookById(UUID id);
}
