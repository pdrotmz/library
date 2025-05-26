package io.github.pdrotmz.libraryAPI.controller;

import io.github.pdrotmz.libraryAPI.dto.author.AuthorRequestDTO;
import io.github.pdrotmz.libraryAPI.dto.author.AuthorResponseDTO;
import io.github.pdrotmz.libraryAPI.model.Author;
import io.github.pdrotmz.libraryAPI.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("author")
public class AuthorController {

    @Autowired
    private AuthorService service;

    @PostMapping("/register")
    public ResponseEntity<AuthorResponseDTO> registerAuthor(@RequestBody AuthorRequestDTO request) {
        AuthorResponseDTO response = service.registerAuthor(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/find-all")
    public ResponseEntity<List<Author>> findAllAuthors() {
        List<Author> authors = service.findAllAuthors();
        return ResponseEntity.status(HttpStatus.OK).body(authors);
    }

    @GetMapping("/filter-by/birth-date/{birthDate}")
    public ResponseEntity<List<Author>> findAuthorByBirthDate(@PathVariable int birthDate) {
        List<Author> authors = service.findAuthorsByBirthDate(birthDate);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(authors);
    }

    @GetMapping("/filter-by/name/{name}")
    public ResponseEntity<List<Author>> findAuthorByName(@PathVariable String name) {
        List<Author> authors = service.findAuthorByName(name);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(authors);
    }

    @PutMapping("/update-by/id/{id}")
    public ResponseEntity<Void> updatedAuthorById(@RequestBody Author author, @PathVariable UUID id) {
        service.updateAuthorById(author, id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/delete-by/id/{id}")
    public ResponseEntity<Void> deleteAuthorById(@PathVariable UUID id) {
        service.deleteAuthorById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
