package org.remusrd.sales.taxes;

import org.remusrd.sales.taxes.product.Basket;
import org.remusrd.sales.taxes.product.Product;
import org.remusrd.sales.taxes.product.Receipt;

import java.math.BigDecimal;

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

        return new Receipt(taxes, totalPrice.add(taxes));
    }
}
