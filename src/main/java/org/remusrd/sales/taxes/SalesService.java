package org.remusrd.sales.taxes;

import org.remusrd.sales.taxes.product.Basket;
import org.remusrd.sales.taxes.product.Product;
import org.remusrd.sales.taxes.product.Receipt;
import org.remusrd.sales.taxes.product.ReceiptProduct;

import java.math.BigDecimal;

import static java.util.stream.Collectors.toUnmodifiableList;

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

        final var receiptProducts = basket.getProducts()
                .stream()
                .map(product -> new ReceiptProduct(product.getName(), product.getTaxedPrice()))
                .collect(toUnmodifiableList());


        return new Receipt(taxes, totalPrice.add(taxes), receiptProducts);
    }
}
