package io.github.pdrotmz.libraryAPI.exception.author;

public class AuthorNotFoundByNameException extends RuntimeException {
    public AuthorNotFoundByNameException(String name) {
        super("Autor de nome: " + name + " não foi encontrado!");
    }
}
