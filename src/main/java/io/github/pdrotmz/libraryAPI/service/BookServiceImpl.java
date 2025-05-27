package io.github.pdrotmz.libraryAPI.service;

import io.github.pdrotmz.libraryAPI.dto.book.BookRequestDTO;
import io.github.pdrotmz.libraryAPI.dto.book.BookResponseDTO;
import io.github.pdrotmz.libraryAPI.exception.book.*;
import io.github.pdrotmz.libraryAPI.model.Author;
import io.github.pdrotmz.libraryAPI.model.Book;
import io.github.pdrotmz.libraryAPI.model.Category;
import io.github.pdrotmz.libraryAPI.projection.BookSummaryProjection;
import io.github.pdrotmz.libraryAPI.projection.BookTitleOnly;
import io.github.pdrotmz.libraryAPI.repository.AuthorRepository;
import io.github.pdrotmz.libraryAPI.repository.BookRepository;
import io.github.pdrotmz.libraryAPI.utils.Validations;
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

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public BookResponseDTO registerBook(BookRequestDTO request) {

        Author authorId = authorRepository.findById(request.authorId())
                .orElseThrow(() -> new BookNotFoundByAuthorIdException(request.authorId()));

        Validations.validatePrice(request.price());
        Validations.validateQuantity(request.quantity());

        Book book = new Book();
        book.setTitle(request.title());
        book.setDescription(request.description());
        book.setPrice(request.price());
        book.setQuantity(request.quantity());
        book.setIsbn(request.isbn());
        book.setCategory(Category.fromLabel(request.category()));
        book.setReleaseDate(request.releaseDate());
        book.setAuthor(authorId);

        bookRepository.save(book);

        return new BookResponseDTO(
                book.getTitle(),
                book.getPrice(),
                book.getQuantity(),
                book.getCategory().getCategory(),
                book.getReleaseDate(),
                book.getAuthor().getName());
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
                .orElseThrow(() -> new BookNotFoundByIdException(id));
    }

    @Override
    public List<BookTitleOnly> findBookByTitle(String title) {
        if(bookRepository.findByTitleContaining(title).isEmpty()) {
            throw new BookNotFoundByTitleException(title);
        }
        return bookRepository.findByTitleContaining(title);
    }

    @Override
    public List<BookSummaryProjection> findBookPriceBetween(BigDecimal initialPrice, BigDecimal finalPrice) {

        if (initialPrice == null || finalPrice == null) {
            throw new InvalidInitialAndFinalPriceException("Preços inicial e final não podem ser nulos");
        }
        if (initialPrice.compareTo(finalPrice) > 0) {
            throw new InvalidInitialPriceException("Preço inicial não pode ser maior que o final");
        }

        return bookRepository.findBookByPriceBetween(initialPrice, finalPrice);
    }

    @Override
    public List<BookSummaryProjection> findBooksByCategory(String category) {
        if(category.isEmpty() || category == null) {
            throw new BookNotFoundByCategoryException(category);
        }
        return bookRepository.findBooksByCategory(category);
    }

    @Override
    public Optional<BookTitleOnly> findBookByIsbn(String isbn) {
        if(bookRepository.findBookByIsbn(isbn).isEmpty() || bookRepository.findBookByIsbn(isbn) == null) {
            throw new BookNotFoundByIsbnException(isbn);
        }
        return bookRepository.findBookByIsbn(isbn);
    }

    @Override
    public List<BookSummaryProjection> findBooksByReleaseDate(String releaseDate) {
        if(releaseDate.isEmpty()) {
            throw new BookNotFoundByReleaseDateException(releaseDate);
        }
        return bookRepository.findBooksByReleaseDate(releaseDate);
    }

    @Override
    public List<BookSummaryProjection> findBooksByAuthorId(UUID authorId) {
        if(authorRepository.findById(authorId).isEmpty()) {
            throw new BookNotFoundByAuthorIdException(authorId);
        }
        return bookRepository.findBooksByAuthorId(authorId);
    }

    @Override
    public void updateBookById(Book book, UUID id) {
        Book updatedBook = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundByIdException(id));

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

