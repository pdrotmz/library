package io.github.pdrotmz.libraryAPI.projection;

import java.math.BigDecimal;

public interface BookTitleOnly {
    String getTitle();
    BigDecimal getPrice();
    int getQuantity();
}
