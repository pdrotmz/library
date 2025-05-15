package io.github.pdrotmz.libraryAPI.service;

import io.github.pdrotmz.libraryAPI.model.Book;
import io.github.pdrotmz.libraryAPI.projection.BookSummaryProjection;
import io.github.pdrotmz.libraryAPI.projection.BookTitleOnly;
import io.github.pdrotmz.libraryAPI.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book registerBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public BookSummaryProjection findBookById(UUID id) {
        return Optional.ofNullable(bookRepository.findBookById(id))
                .orElseThrow(() -> new EntityNotFoundException("Erro ao achar esse livro!"));
    }

    @Override
    public BookTitleOnly findBookByTitle(String title) {
        return Optional.ofNullable(bookRepository.findBookByTitle(title))
                .orElseThrow(() -> new RuntimeException("Erro ao achar o livro!"));
    }

    @Override
    public void updateBookById(Book book, UUID id) {
        Book updatedBook = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado!"));

        updatedBook.setTitle(book.getTitle());
        updatedBook.setDescription(book.getDescription());
        updatedBook.setPrice(book.getPrice());
        updatedBook.setQuantity(book.getQuantity());
        updatedBook.setIsbn(book.getIsbn());

        bookRepository.save(book);
    }

    @Override
    @Transactional
    public void deleteBookById(UUID id) {
        bookRepository.deleteById(id);
    }


}

