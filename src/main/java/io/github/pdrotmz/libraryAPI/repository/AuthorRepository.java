package io.github.pdrotmz.libraryAPI.repository;

import io.github.pdrotmz.libraryAPI.dto.author.AuthorResponseDTO;
import io.github.pdrotmz.libraryAPI.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID> {

    @Query(value = "SELECT * FROM author a WHERE a.name =: name", nativeQuery = true)
    AuthorResponseDTO findAuthorByName(@Param("name") String name);
}
