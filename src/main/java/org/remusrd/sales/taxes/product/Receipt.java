package org.remusrd.sales.taxes.product;

import java.math.BigDecimal;
import java.util.List;

public class Receipt {
    public final List<ReceiptProduct> products;
    private final BigDecimal salesTaxes;
    private final BigDecimal total;

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
