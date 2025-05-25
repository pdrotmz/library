package io.github.pdrotmz.libraryAPI.repository;

import io.github.pdrotmz.libraryAPI.dto.author.AuthorResponseDTO;
import io.github.pdrotmz.libraryAPI.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID> {

    @Query(value = "SELECT * FROM tb_authors a WHERE a.name LIKE %?1%", nativeQuery = true)
    List<Author> findAuthorByName(String name);

    @Query(value = "SELECT * FROM tb_authors a WHERE a.birth_date = :birthDate", nativeQuery = true)
    List<Author> findAuthorsByBirthDate(@Param("birthDate") int birthDate);
}
