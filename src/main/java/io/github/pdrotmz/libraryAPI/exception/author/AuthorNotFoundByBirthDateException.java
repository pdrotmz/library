package io.github.pdrotmz.libraryAPI.exception.author;

public class AuthorNotFoundByBirthDateException extends RuntimeException {
    public AuthorNotFoundByBirthDateException(int birthDate) {
        super("Nenhum autor encontrado no ano de: " + birthDate);
    }
}
