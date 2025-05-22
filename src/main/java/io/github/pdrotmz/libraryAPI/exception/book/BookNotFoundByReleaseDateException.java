package io.github.pdrotmz.libraryAPI.exception.book;

public class BookNotFoundByReleaseDateException extends RuntimeException {
    public BookNotFoundByReleaseDateException(String releaseDate) {
        super("Nenhum livro encontrado para o ano: " + releaseDate);
    }
}
