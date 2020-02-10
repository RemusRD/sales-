package org.remusrd.sales.taxes.product;

import java.math.BigDecimal;

public class ExemptProduct implements Product {
    private final String name;
    private final BigDecimal price;

    public ExemptProduct(String name, BigDecimal price) {
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
    public BigDecimal getTaxAmount() {
        return TaxStrategy.exempt().apply(price);
    }

}
