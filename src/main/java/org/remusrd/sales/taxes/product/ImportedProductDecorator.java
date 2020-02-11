package org.remusrd.sales.taxes.product;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.remusrd.sales.taxes.product.TaxStrategy.importation;

public class ImportedProductDecorator implements Product {
    private final Product product;
    private final TaxStrategy calculationMethod;

    public ImportedProductDecorator(Product product) {
        this.product = product;
        this.calculationMethod = importation().combine(product.getTaxCalculationMethod());
    }

    @Override
    public String getName() {
        return product.getName();
    }

    @Override
    public BigDecimal getPrice() {
        return product.getPrice();
    }

    @Override
    public BigDecimal getTaxAmount() {
        final var taxAmount = addImportTaxToCurrent();
        return roundToTwoDecimals(taxAmount);
    }

    @Override
    public TaxStrategy getTaxCalculationMethod() {
        return product.getTaxCalculationMethod();
    }

    private BigDecimal addImportTaxToCurrent() {
        return calculationMethod.apply(product.getPrice());
    }

    private BigDecimal roundToTwoDecimals(BigDecimal taxAmount) {
        return taxAmount.divide(new BigDecimal("0.05"), 0, RoundingMode.UP).multiply(new BigDecimal("0.05"));
    }
}
