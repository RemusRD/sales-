package product;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Receipt {
    private final BigDecimal salesTaxes;
    private final BigDecimal total;

    public Receipt(BigDecimal salesTaxes, BigDecimal total) {
        this.salesTaxes = salesTaxes.setScale(2, RoundingMode.HALF_UP);
        this.total = total.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getSalesTaxes() {
        return salesTaxes;
    }

    public BigDecimal getTotal() {
        return total;
    }
}
