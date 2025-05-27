package io.github.pdrotmz.libraryAPI.service;

import io.github.pdrotmz.libraryAPI.dto.book.BookRequestDTO;
import io.github.pdrotmz.libraryAPI.dto.book.BookResponseDTO;
import io.github.pdrotmz.libraryAPI.model.Author;
import io.github.pdrotmz.libraryAPI.model.Book;
import io.github.pdrotmz.libraryAPI.model.Category;
import io.github.pdrotmz.libraryAPI.repository.AuthorRepository;
import io.github.pdrotmz.libraryAPI.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @InjectMocks
    private BookServiceImpl bookService;

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private Category category;

    @Test
    void shouldRegisterBookSuccessfully() {
        UUID authorId = UUID.randomUUID();
        Author author = new Author(authorId, "Huey Freeman", 2003, new ArrayList<>());

        BookRequestDTO request = new BookRequestDTO(
                "The Boondocks Book",
                "Revolução e Consciência",
                new BigDecimal("1500.00"),
                12,
                "154684",
                "mangá",
                "1980",
                authorId
        );

        Book book = new Book();
        book.setTitle(request.title());
        book.setDescription(request.description());
        book.setPrice(request.price());
        book.setQuantity(request.quantity());
        book.setIsbn(request.isbn());
        book.setCategory(Category.fromLabel(request.category()));
        book.setReleaseDate(request.releaseDate());
        book.setAuthor(author);

        when(authorRepository.findById(authorId)).thenReturn(Optional.of(author));
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        BookResponseDTO response = bookService.registerBook(request);

        assertEquals("The Boondocks Book", response.title());
        assertEquals(new BigDecimal("1500.00"), response.price());
        assertEquals(12, response.quantity());
        assertEquals("mangá", response.category());
        assertEquals("1980", response.releaseDate());
        assertEquals("Huey Freeman", response.authorName());

        verify(authorRepository, times(1)).findById(authorId);
        verify(bookRepository, times(1)).save(any(Book.class));
    }
}
