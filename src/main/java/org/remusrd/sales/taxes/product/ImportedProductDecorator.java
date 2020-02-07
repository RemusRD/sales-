package product;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ImportedProductDecorator implements Product {
    private final Product product;

    public ImportedProductDecorator(Product product) {
        this.product = product;
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

    private BigDecimal addImportTaxToCurrent() {
        return product.getPrice().multiply(new BigDecimal(5)).divide(new BigDecimal(100), 2, RoundingMode.HALF_UP).add(product.getTaxAmount());
    }

    private BigDecimal roundToTwoDecimals(BigDecimal taxAmount) {
        return taxAmount.divide(new BigDecimal("0.05"), 0, RoundingMode.UP).multiply(new BigDecimal("0.05"));
    }
}
