package io.github.pdrotmz.libraryAPI.service;

import io.github.pdrotmz.libraryAPI.dto.author.AuthorRequestDTO;
import io.github.pdrotmz.libraryAPI.dto.author.AuthorResponseDTO;
import io.github.pdrotmz.libraryAPI.model.Author;
import io.github.pdrotmz.libraryAPI.repository.AuthorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceTest {

    @InjectMocks
    private AuthorServiceImpl authorService;

    @Mock
    AuthorRepository authorRepository;

    @Test
    void shouldRegisterAuthorSuccessfully() {
        AuthorRequestDTO request = new AuthorRequestDTO(
                "Huey Freeman",
                2003
        );
        UUID authorId = UUID.randomUUID();
        Author savedAuthor = new Author(authorId, "Huey Freeman", 2003, new ArrayList<>());

        when(authorRepository.save(any(Author.class))).thenReturn(savedAuthor);

        AuthorResponseDTO response = authorService.registerAuthor(request);

        assertEquals(authorId, response.id());
        assertEquals("Huey Freeman", response.name());
        assertEquals(2003, response.birthDate());
        assertTrue(response.books().isEmpty());

        Mockito.verify(authorRepository, Mockito.times(1)).save(any(Author.class));
    }

    @Test
    void shouldFindAuthorsByNameSuccessfully() {
        String name = "Huey Freeman";
        List<Author> authors = List.of(
                new Author(UUID.randomUUID(), name, 2003, new ArrayList<>())
        );

        when(authorRepository.findAuthorByName(name)).thenReturn(authors);

        List<Author> result = authorService.findAuthorByName(name);

        assertEquals(1, result.size());
        assertEquals(name, result.get(0).getName());
        verify(authorRepository, times(1)).findAuthorByName("Huey Freeman");
    }

    @Test
    void shouldFindAuthorByBirthDateSuccessfully() {
        int birthDate = 2003;
        List<Author> authors = List.of(
                new Author(UUID.randomUUID(), "Huey Freeman", birthDate, new ArrayList<>())
        );

        when(authorRepository.findAuthorsByBirthDate(birthDate)).thenReturn(authors);

        List<Author> result = authorService.findAuthorsByBirthDate(birthDate);

        assertEquals(1, result.size());
        assertEquals(birthDate, result.get(0).getBirthDate());
        verify(authorRepository, times(1)).findAuthorsByBirthDate(2003);
    }


}
