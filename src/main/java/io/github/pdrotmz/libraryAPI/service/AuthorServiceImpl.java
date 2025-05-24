package io.github.pdrotmz.libraryAPI.service;

import io.github.pdrotmz.libraryAPI.dto.author.AuthorRequestDTO;
import io.github.pdrotmz.libraryAPI.dto.author.AuthorResponseDTO;
import io.github.pdrotmz.libraryAPI.exception.author.AuthorNotFoundByBirthDateException;
import io.github.pdrotmz.libraryAPI.model.Author;
import io.github.pdrotmz.libraryAPI.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository repository;

    @Override
    public AuthorResponseDTO registerAuthor(AuthorRequestDTO request) {
        Author author = new Author();
        author.setName(request.name());
        author.setBirthDate(request.birthDate());

        repository.save(author);

        return new AuthorResponseDTO(author.getId(), author.getName(), author.getBirthDate(), author.getBooks());
    }

    @Override
    public List<Author> findAuthorsByBirthDate(int birthDate) {
        if(repository.findAuthorsByBirthDate(birthDate).isEmpty()) {
            throw new AuthorNotFoundByBirthDateException(birthDate);
        }
        return repository.findAuthorsByBirthDate(birthDate);
    }

    @Override
    public List<Author> findAllAuthors() {
        return repository.findAll();
    }
}
