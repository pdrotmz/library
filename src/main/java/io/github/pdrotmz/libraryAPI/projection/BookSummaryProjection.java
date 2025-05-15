package io.github.pdrotmz.libraryAPI.projection;

import java.math.BigDecimal;

public interface BookSummaryProjection {
    String getTitle();
    BigDecimal getPrice();
    int getQuantity();
}
