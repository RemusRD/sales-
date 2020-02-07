import product.Basket;
import product.Product;
import product.Receipt;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SalesService {
    public Receipt purchase(Basket basket) {
        final var totalPrice = basket.getProducts()
                .stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        final var taxes = basket.getProducts()
                .stream()
                .map(Product::getTaxAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);


        final var roundedTaxes = taxes.setScale(2, RoundingMode.HALF_UP);


        return new Receipt(roundedTaxes, totalPrice.add(taxes));
    }
}
