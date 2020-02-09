package org.remusrd.sales.taxes.product;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.StringJoiner;

public class ReceiptProduct {
    private final String name;
    private final BigDecimal price;

    public ReceiptProduct(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final ReceiptProduct that = (ReceiptProduct) o;

        if (!Objects.equals(name, that.name)) return false;
        return Objects.equals(price, that.price);
    }
}
