package io.github.pdrotmz.libraryAPI.dto.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record BookRequestDTO(

        @NotBlank(message = "Campo obrigatório")
        String title,
        @NotBlank(message = "Campo obrigatório")
        String description,
        @NotNull(message = "Campo obrigatório")
        BigDecimal price,
        @NotNull(message = "Campo obrigatório")
        int quantity,
        @NotBlank(message = "Campo obrigatório")
        String isbn,
        @NotBlank(message = "Campo obrigatório")
        String category,
        @NotBlank(message = "Campo obrigatório")
        String releaseDate,
        @NotNull(message = "Campo obrigatório")
        UUID authorId
) {}
