package org.remusrd.sales.taxes.product;

import java.math.BigDecimal;

public interface Product {

    String getName();

    BigDecimal getPrice();

    BigDecimal getTaxAmount();

    TaxStrategy getTaxCalculationMethod();

    default BigDecimal getTaxedPrice() {
        return this.getPrice().add(getTaxAmount());
    }
}
