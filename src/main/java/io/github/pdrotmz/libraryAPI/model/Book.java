package io.github.pdrotmz.libraryAPI.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Entity(name = "books")
@Table(name = "tb_books")
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "title", updatable = true, length = 150, nullable = false)
    private String title;

    @Column(name = "description", length = 250, updatable = true, nullable = false)
    private String description;

    @Column(name = "price", nullable = false, precision = 6, scale = 2)
    private BigDecimal price;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "isbn", nullable = false, length = 20)
    private String isbn;
}
