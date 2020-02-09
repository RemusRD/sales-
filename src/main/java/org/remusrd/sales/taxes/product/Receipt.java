package org.remusrd.sales.taxes.product;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Receipt {
    private final BigDecimal salesTaxes;
    private final BigDecimal total;

    public Receipt(BigDecimal salesTaxes, BigDecimal total) {
        this.salesTaxes = salesTaxes;
        this.total = total;
    }

    public BigDecimal getSalesTaxes() {
        return salesTaxes;
    }

    public BigDecimal getTotal() {
        return total;
    }
}
