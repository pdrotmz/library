package io.github.pdrotmz.libraryAPI.exception.book;

public class InvalidPriceException extends RuntimeException {
    public InvalidPriceException() {
        super("O preço informado precisa ser maior que zero.");
    }
}
