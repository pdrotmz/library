package io.github.pdrotmz.libraryAPI.model;

import io.github.pdrotmz.libraryAPI.utils.LocalDataTimeConverter;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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

    @Column(name = "isbn", nullable = false, length = 20, unique = true)
    private String isbn;

    @Column(name = "category", nullable = false, length = 50, updatable = true)
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name = "release_date", nullable = false)
    private String releaseDate;

    /* @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private Author author; */

    @Column(name = "created_at")
    @Convert(converter = LocalDataTimeConverter.class)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @Convert(converter = LocalDataTimeConverter.class)
    private LocalDateTime updatedAt;

    @PrePersist
    public void onPrePersist() {
        this.setCreatedAt(LocalDateTime.now());
        this.setUpdatedAt(LocalDateTime.now());
    }

    @PreUpdate
    public void onPreUpdate() {
        this.setUpdatedAt(LocalDateTime.now());
    }
}
