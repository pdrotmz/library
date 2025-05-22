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
}
