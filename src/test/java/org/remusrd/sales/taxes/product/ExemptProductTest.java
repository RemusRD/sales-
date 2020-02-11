package org.remusrd.sales.taxes.product;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class ExemptProductTest {
    @Test
    public void shouldNotApplyAnyTaxes() {
        final var result = new ExemptProduct("anyName", new BigDecimal("10.34"));

        assertEquals(result.getTaxAmount(), BigDecimal.ZERO);
    }
}
