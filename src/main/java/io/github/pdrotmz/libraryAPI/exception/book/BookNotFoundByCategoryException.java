package io.github.pdrotmz.libraryAPI.exception.book;

public class BookNotFoundByCategoryException extends RuntimeException {
    public BookNotFoundByCategoryException(String category) {
        super("Não foram encontrado livros com a categoria: " + category);
    }
}
