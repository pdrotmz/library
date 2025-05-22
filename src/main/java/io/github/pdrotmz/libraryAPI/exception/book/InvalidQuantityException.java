package io.github.pdrotmz.libraryAPI.exception.book;

public class InvalidQuantityException extends RuntimeException {
    public InvalidQuantityException() {
        super("A quantidade informada precisa ser maior que zero.");
    }
}
