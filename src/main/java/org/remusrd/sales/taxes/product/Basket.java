package org.remusrd.sales.taxes.product;

import java.util.List;

public class Basket {
    private final List<Product> products;

    public Basket(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }
}
