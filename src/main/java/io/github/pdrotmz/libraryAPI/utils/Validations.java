package io.github.pdrotmz.libraryAPI.utils;

import io.github.pdrotmz.libraryAPI.exception.book.InvalidPriceException;
import io.github.pdrotmz.libraryAPI.exception.book.InvalidQuantityException;

import java.math.BigDecimal;

public class Validations {

    public static void validatePrice(BigDecimal price) {
        if(price == null || price.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidPriceException();
        }
    }

    public static void validateQuantity(Integer quantity) {
        if(quantity == null || quantity < 0) {
            throw new InvalidQuantityException();
        }
    }
}
