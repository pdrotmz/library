package io.github.pdrotmz.libraryAPI.service;

import io.github.pdrotmz.libraryAPI.dto.author.AuthorRequestDTO;
import io.github.pdrotmz.libraryAPI.dto.author.AuthorResponseDTO;
import io.github.pdrotmz.libraryAPI.exception.author.AuthorNotFoundByBirthDateException;
import io.github.pdrotmz.libraryAPI.exception.author.AuthorNotFoundByNameException;
import io.github.pdrotmz.libraryAPI.exception.book.BookNotFoundByIdException;
import io.github.pdrotmz.libraryAPI.model.Author;
import io.github.pdrotmz.libraryAPI.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository repository;

    @Override
    public AuthorResponseDTO registerAuthor(AuthorRequestDTO request) {
        Author author = Author.builder()
                .name(request.name())
                .birthDate(request.birthDate())
                .books(new ArrayList<>())
                .build();

        Author savedAuthor = repository.save(author);

        return new AuthorResponseDTO(savedAuthor.getId(), savedAuthor.getName(), savedAuthor.getBirthDate(), savedAuthor.getBooks());
    }

    @Override
    public List<Author> findAuthorByName(String name) {
        List<Author> authors = repository.findAuthorByName(name);
        if(authors.isEmpty()) {
            throw new AuthorNotFoundByNameException(name);
        }
        return authors;
    }

    @Override
    public List<Author> findAuthorsByBirthDate(int birthDate) {
        List<Author> authors = repository.findAuthorsByBirthDate(birthDate);
        if(authors.isEmpty()) {
            throw new AuthorNotFoundByBirthDateException(birthDate);
        }
        return authors;
    }

    @Override
    public List<Author> findAllAuthors() {
        return repository.findAll();
    }

    @Override
    public void updateAuthorById(Author author,UUID id) {
        Author updatedAuthor = repository.findById(id)
                .orElseThrow(() -> new BookNotFoundByIdException(id));

        updatedAuthor.setName(author.getName());
        updatedAuthor.setBirthDate(author.getBirthDate());

        repository.save(updatedAuthor);
    }

    @Override
    public void deleteAuthorById(UUID id) {
        repository.deleteById(id);
    }
}
