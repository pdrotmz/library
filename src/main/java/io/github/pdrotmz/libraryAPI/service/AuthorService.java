package io.github.pdrotmz.libraryAPI.service;

import io.github.pdrotmz.libraryAPI.dto.author.AuthorRequestDTO;
import io.github.pdrotmz.libraryAPI.dto.author.AuthorResponseDTO;
import io.github.pdrotmz.libraryAPI.model.Author;

import java.util.List;

public interface AuthorService {
    AuthorResponseDTO registerAuthor(AuthorRequestDTO request);
    List<Author> findAllAuthors();
}
