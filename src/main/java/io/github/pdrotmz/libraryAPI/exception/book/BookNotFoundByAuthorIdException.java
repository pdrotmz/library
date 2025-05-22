package io.github.pdrotmz.libraryAPI.exception.book;

import java.util.UUID;

public class BookNotFoundByAuthorIdException extends RuntimeException {
  public BookNotFoundByAuthorIdException(UUID authorId) {
    super("Autor com id" + authorId + " não foi encontrado!");
  }
}
