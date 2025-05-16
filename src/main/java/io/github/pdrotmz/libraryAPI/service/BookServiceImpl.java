package io.github.pdrotmz.libraryAPI.service;

import io.github.pdrotmz.libraryAPI.model.Book;
import io.github.pdrotmz.libraryAPI.projection.BookSummaryProjection;
import io.github.pdrotmz.libraryAPI.projection.BookTitleOnly;
import io.github.pdrotmz.libraryAPI.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
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
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public List<BookSummaryProjection> findAllSummaries() {
        return bookRepository.findAllSummaries();
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
    public List<BookSummaryProjection> findBookPriceBetween(BigDecimal initialPrice, BigDecimal finalPrice) {

        if (initialPrice == null || finalPrice == null) {
            throw new IllegalArgumentException("Preços inicial e final não podem ser nulos");
        }
        if (initialPrice.compareTo(finalPrice) > 0) {
            throw new IllegalArgumentException("Preço inicial não pode ser maior que o final");
        }

        return bookRepository.findBookByPriceBetween(initialPrice, finalPrice);
    }

    @Override
    public List<BookSummaryProjection> findBooksByCategory(String category) {
        if(category.isEmpty() || category == null) {
            throw new RuntimeException("Erro ao carregar livros por categoria!");
        }
        return bookRepository.findBooksByCategory(category);
    }

    @Override
    public Optional<BookTitleOnly> findBookByIsbn(String isbn) {
        if(bookRepository.findBookByIsbn(isbn).isEmpty() || bookRepository.findBookByIsbn(isbn) == null) {
            throw new RuntimeException("Erro ao procurar por isbn");
        }
        return bookRepository.findBookByIsbn(isbn);
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
        updatedBook.setCategory(book.getCategory());

        bookRepository.save(updatedBook);
    }

    @Override
    @Transactional
    public void deleteBookById(UUID id) {
        bookRepository.deleteById(id);
    }


}

