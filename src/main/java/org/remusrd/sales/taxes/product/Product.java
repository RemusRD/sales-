package product;

import java.math.BigDecimal;

public interface Product {

    String getName();

    BigDecimal getPrice();

    BigDecimal getTaxAmount();

    default BigDecimal getTaxedPrice() {
        return this.getPrice().add(getTaxAmount());
    }
}
