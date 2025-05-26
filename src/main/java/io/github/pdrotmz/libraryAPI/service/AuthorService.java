package io.github.pdrotmz.libraryAPI.service;

import io.github.pdrotmz.libraryAPI.dto.author.AuthorRequestDTO;
import io.github.pdrotmz.libraryAPI.dto.author.AuthorResponseDTO;
import io.github.pdrotmz.libraryAPI.model.Author;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AuthorService {
    AuthorResponseDTO registerAuthor(AuthorRequestDTO request);
    List<Author> findAuthorByName(String name);
    List<Author> findAuthorsByBirthDate(int birthDate);
    List<Author> findAllAuthors();
    void updateAuthorById(Author author, UUID id);
    void deleteAuthorById(UUID id);
}
