import org.junit.Before;
import org.junit.Test;
import product.Basket;
import product.ExemptProduct;
import product.ImportedProductDecorator;
import product.TaxedProduct;

import java.math.BigDecimal;
import java.util.List;

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
        final var price1 = new BigDecimal("12.49");
        final var price2 = new BigDecimal("14.99");
        final var price3 = new BigDecimal("0.85");

        final var basket = new Basket(
                List.of(
                        new ExemptProduct("book", price1),
                        new TaxedProduct("music CD", price2),
                        new ExemptProduct("chocolate bar", price3)
                )
        );

        final var receipt = sut.purchase(basket);

        assertThat(receipt.getSalesTaxes(), is(new BigDecimal("1.50")));
        assertThat(receipt.getTotal(), is(new BigDecimal("29.83")));

    }

    //TODO: show product price + tax in receipt
    @Test//test case 2
    public void givenABasketWithImportedProductsItShouldApplyTaxes() {
        final var price1 = new BigDecimal("10.00");
        final var price2 = new BigDecimal("47.50");

        final var basket = new Basket(
                List.of(
                        new ImportedProductDecorator(new ExemptProduct("imported box of chocolates", price1)),
                        new ImportedProductDecorator(new TaxedProduct("imported bottle of perfume", price2))
                )
        );

        final var receipt = sut.purchase(basket);


        assertThat(receipt.getSalesTaxes(), is(new BigDecimal("7.65")));
        assertThat(receipt.getTotal(), is(new BigDecimal("65.15")));
    }

    @Test//test case 3
    public void givenABasketWithImportedAndNonImportedProductsItShouldApplyTaxes() {
        final var price1 = new BigDecimal("27.99");
        final var price2 = new BigDecimal("18.99");
        final var price3 = new BigDecimal("9.75");
        final var price4 = new BigDecimal("11.25");

        final var basket = new Basket(
                List.of(
                        new ImportedProductDecorator(new TaxedProduct("imported bottle of perfume", price1)),
                        new TaxedProduct("bottle of perfume", price2),
                        new ExemptProduct("packet of headache pills", price3),
                        new ImportedProductDecorator(new ExemptProduct("imported box of chocolates", price4))
                )
        );

        final var receipt = sut.purchase(basket);


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

        assertThat(receipt.getSalesTaxes(), is( new BigDecimal("0.00")));
        assertThat(receipt.getTotal(), is(new BigDecimal("13.34")));

    }

}
