package org.remusrd.sales.taxes.product;

import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TaxedProductTest {


    @Test
    public void shouldAddTaxesToBasePrice() {
        final var result = new TaxedProduct("music CD", new BigDecimal("14.99"));

        assertThat(result.getTaxAmount(), is(new BigDecimal("1.50")));
    }
}
