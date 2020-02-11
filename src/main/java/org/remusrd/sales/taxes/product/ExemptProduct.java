package org.remusrd.sales.taxes.product;

import java.math.BigDecimal;

public class ExemptProduct implements Product {
    private final String name;
    private final BigDecimal price;
    private final TaxStrategy calculationMethod;

    public ExemptProduct(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
        this.calculationMethod = TaxStrategy.exempt();
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public BigDecimal getTaxAmount() {
        return calculationMethod.apply(price);
    }

    @Override
    public TaxStrategy getTaxCalculationMethod() {
        return calculationMethod;
    }

}
