package io.github.pdrotmz.libraryAPI.exception.book;

public class BookNotFoundByTitleException extends RuntimeException {
    public BookNotFoundByTitleException(String title) {
        super("O livro com nome" + title + " não foi encontrado!");
    }
}
