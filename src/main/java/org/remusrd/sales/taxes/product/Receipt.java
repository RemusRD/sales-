package org.remusrd.sales.taxes.product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class Receipt {
    private final BigDecimal salesTaxes;
    private final BigDecimal total;
    public final List<ReceiptProduct> products;

    public Receipt(BigDecimal salesTaxes, BigDecimal total, List<ReceiptProduct> products) {
        this.salesTaxes = salesTaxes;
        this.total = total;
        this.products = products;
    }

    public BigDecimal getSalesTaxes() {
        return salesTaxes;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public List<ReceiptProduct> getProducts() {
        return products;
    }
}
