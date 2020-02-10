package org.remusrd.sales.taxes.product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.UnaryOperator;

public interface TaxStrategy extends UnaryOperator<BigDecimal> {

    static TaxStrategy exempt() {
        return (amount) -> BigDecimal.ZERO;
    }

    static TaxStrategy basic() {
        return (amount) -> amount.multiply(BigDecimal.TEN).divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);
    }

    static TaxStrategy importation() {
        return (amount) -> amount.multiply(new BigDecimal(5)).divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);
    }

    default TaxStrategy combine(TaxStrategy after) {
        return value -> after.apply(this.apply(value));
    }
}
