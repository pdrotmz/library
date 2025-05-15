package io.github.pdrotmz.libraryAPI.service;

import io.github.pdrotmz.libraryAPI.model.Book;
import io.github.pdrotmz.libraryAPI.projection.BookSummaryProjection;
import io.github.pdrotmz.libraryAPI.projection.BookTitleOnly;

import java.util.UUID;

public interface BookService {
    Book registerBook(Book book);
    BookSummaryProjection findBookById(UUID id);
    BookTitleOnly findBookByTitle(String title);
    void updateBookById(Book book, UUID id);
    void deleteBookById(UUID id);
}
