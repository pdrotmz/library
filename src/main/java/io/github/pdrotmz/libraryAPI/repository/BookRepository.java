package io.github.pdrotmz.libraryAPI.repository;

import io.github.pdrotmz.libraryAPI.model.Book;
import io.github.pdrotmz.libraryAPI.model.Category;
import io.github.pdrotmz.libraryAPI.projection.BookSummaryProjection;
import io.github.pdrotmz.libraryAPI.projection.BookTitleOnly;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {

    @Query(value = "SELECT b.title, b.price, b.quantity FROM tb_books b WHERE b.id = ?1", nativeQuery = true)
    BookSummaryProjection findBookById(UUID id);

    @Query(value = "SELECT b.title, b.price, b.quantity FROM tb_books b WHERE b.title LIKE = ?1", nativeQuery = true)
    BookTitleOnly findBookByTitle(@Param("title") String title);

    @Query(value = "SELECT b.title, b.price, b.quantity FROM tb_books b", nativeQuery = true)
    List<BookSummaryProjection> findAllSummaries();

    @Query(value = "SELECT * FROM tb_books b WHERE b.price BETWEEN :initialPrice AND :finalPrice", nativeQuery = true)
    List<BookSummaryProjection> findBookByPriceBetween(@Param("initialPrice") BigDecimal initialPrice,
                                                       @Param("finalPrice") BigDecimal finalPrice);

    @Query(value = "SELECT * FROM tb_books b WHERE b.category =:category", nativeQuery = true)
    List<BookSummaryProjection> findBooksByCategory(@Param("category")String category);

    @Query(value = "SELECT * FROM tb_books b WHERE b.isbn =:isbn", nativeQuery = true)
    Optional<BookTitleOnly> findBookByIsbn(@Param("isbn") String isbn);

    boolean existsByIsbn(String isbn);
}
