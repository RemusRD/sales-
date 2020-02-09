package org.remusrd.sales.taxes;

import org.junit.Before;
import org.junit.Test;
import org.remusrd.sales.taxes.product.Basket;
import org.remusrd.sales.taxes.product.ExemptProduct;
import org.remusrd.sales.taxes.product.ImportedProductDecorator;
import org.remusrd.sales.taxes.product.ReceiptProduct;
import org.remusrd.sales.taxes.product.TaxedProduct;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SalesServiceTest {

    private SalesService sut;

    @Before
    public void setUp() {
        sut = new SalesService();
    }

    @Test//test case 1
    public void givenABasketContainingNonExemptProductsShouldCalculateTaxes() {
        final String name1 = "book";
        final String name2 = "music CD";
        final String name3 = "chocolate bar";

        final var basket = new Basket(
                List.of(
                        new ExemptProduct(name1, new BigDecimal("12.49")),
                        new TaxedProduct(name2, new BigDecimal("14.99")),
                        new ExemptProduct(name3, new BigDecimal("0.85"))
                )
        );

        final var receipt = sut.purchase(basket);

        assertThat(receipt.getProducts(), hasItem(new ReceiptProduct(name1, new BigDecimal("12.49"))));
        assertThat(receipt.getProducts(), hasItem(new ReceiptProduct(name2, new BigDecimal("16.49"))));
        assertThat(receipt.getProducts(), hasItem(new ReceiptProduct(name3, new BigDecimal("0.85"))));

        assertThat(receipt.getSalesTaxes(), is(new BigDecimal("1.50")));
        assertThat(receipt.getTotal(), is(new BigDecimal("29.83")));

    }

    @Test//test case 2
    public void givenABasketWithImportedProductsItShouldApplyTaxes() {
        final var name1 = "imported box of chocolates";
        final var name2 = "imported bottle of perfume";

        final var basket = new Basket(
                List.of(
                        new ImportedProductDecorator(new ExemptProduct(name1, new BigDecimal("10.00"))),
                        new ImportedProductDecorator(new TaxedProduct(name2, new BigDecimal("47.50")))
                )
        );

        final var receipt = sut.purchase(basket);


        assertThat(receipt.getProducts(), hasItem(new ReceiptProduct(name1, new BigDecimal("10.50"))));
        assertThat(receipt.getProducts(), hasItem(new ReceiptProduct(name2, new BigDecimal("54.65"))));


        assertThat(receipt.getSalesTaxes(), is(new BigDecimal("7.65")));
        assertThat(receipt.getTotal(), is(new BigDecimal("65.15")));
    }

    @Test//test case 3
    public void givenABasketWithImportedAndNonImportedProductsItShouldApplyTaxes() {

        final var name1 = "imported bottle of perfume";
        final var name2 = "bottle of perfume";
        final var name3 = "packet of headache pills";
        final var name4 = "imported box of chocolates";

        final var basket = new Basket(
                List.of(
                        new ImportedProductDecorator(new TaxedProduct(name1, new BigDecimal("27.99"))),
                        new TaxedProduct(name2, new BigDecimal("18.99")),
                        new ExemptProduct(name3, new BigDecimal("9.75")),
                        new ImportedProductDecorator(new ExemptProduct(name4, new BigDecimal("11.25")))
                )
        );

        final var receipt = sut.purchase(basket);

        assertThat(receipt.getProducts(), hasItem(new ReceiptProduct(name1, new BigDecimal("32.19"))));
        assertThat(receipt.getProducts(), hasItem(new ReceiptProduct(name2, new BigDecimal("20.89"))));
        assertThat(receipt.getProducts(), hasItem(new ReceiptProduct(name3, new BigDecimal("9.75"))));
        assertThat(receipt.getProducts(), hasItem(new ReceiptProduct(name4, new BigDecimal("11.85"))));

        assertThat(receipt.getSalesTaxes(), is(new BigDecimal("6.70")));
        assertThat(receipt.getTotal(), is(new BigDecimal("74.68")));
    }

    @Test
    public void givenABasketWithExemptProductsReceiptTaxesShouldBeZero() {
        final var basket = new Basket(
                List.of(
                        new ExemptProduct("book", new BigDecimal("12.49")),
                        new ExemptProduct("chocolate bar", new BigDecimal("0.85"))
                )
        );

        final var receipt = sut.purchase(basket);

        assertThat(receipt.getSalesTaxes(), is(BigDecimal.ZERO));
        assertThat(receipt.getTotal(), is(new BigDecimal("13.34")));

    }

}
