package io.github.pdrotmz.libraryAPI.exception.book;

import java.util.UUID;

public class BookNotFoundByIdException extends RuntimeException {
    public BookNotFoundByIdException(UUID id) {
        super("O livro com id: "  + id + " não foi encontrado!");
    }
}
