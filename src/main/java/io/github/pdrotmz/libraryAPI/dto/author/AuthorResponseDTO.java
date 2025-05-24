package io.github.pdrotmz.libraryAPI.dto.author;

import io.github.pdrotmz.libraryAPI.model.Book;

import java.util.List;
import java.util.UUID;

public record AuthorResponseDTO(
        UUID id,
        String name,
        int birthDate,
        List<Book> books
) {
}
