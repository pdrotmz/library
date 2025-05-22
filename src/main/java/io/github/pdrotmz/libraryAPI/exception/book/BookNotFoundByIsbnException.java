package io.github.pdrotmz.libraryAPI.exception.book;

public class BookNotFoundByIsbnException extends RuntimeException {
    public BookNotFoundByIsbnException(String isbn) {
        super("isbn : " + isbn + " incorreto ou inválido!");
    }
}
