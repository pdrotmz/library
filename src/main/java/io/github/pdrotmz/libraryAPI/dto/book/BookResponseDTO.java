package io.github.pdrotmz.libraryAPI.dto.book;

import java.math.BigDecimal;

public record BookResponseDTO(
        String title,
        BigDecimal price,
        int quantity,
        String category,
        String releaseDate,
        String authorName
) {
}
