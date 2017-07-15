package com.ve.salestaxes.policies;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

import com.ve.salestaxes.policies.ExemptSalesTaxPolicy;
import com.ve.salestaxes.policies.SalesTaxPolicy;

/**
 * 
 * @author Valerio Emanuele
 * This class tests if the sales tax round method works correctly with all possible decimal or rather that
 * the amount is rounded up to the nearest 0.05 value.
 * 
 * Because is not possible instantiate abstract class SalesTaxPolicy, in order to test the salesTaxRound method
 * it's instantiated a ExemptSalesTaxPolicy object
 */
public class SalesTaxPolicyTest
{
	@Test
	public void testSalesTaxRound()
	{
		SalesTaxPolicy bookStPolicy = new ExemptSalesTaxPolicy();
		
		assertEquals(new BigDecimal("1.50"), bookStPolicy.salesTaxRound(new BigDecimal("1.499")));
		assertEquals(new BigDecimal("1.00"), bookStPolicy.salesTaxRound(new BigDecimal("0.999")));
		assertEquals(new BigDecimal("21.25"), bookStPolicy.salesTaxRound(new BigDecimal("21.23")));
		assertEquals(new BigDecimal("0.05"), bookStPolicy.salesTaxRound(new BigDecimal("0.04")));
		assertEquals(new BigDecimal("1.15"), bookStPolicy.salesTaxRound(new BigDecimal("1.11")));
		assertEquals(new BigDecimal("100.25"), bookStPolicy.salesTaxRound(new BigDecimal("100.22")));
		assertEquals(new BigDecimal("1.55"), bookStPolicy.salesTaxRound(new BigDecimal("1.55")));
		assertEquals(new BigDecimal("100.00"), bookStPolicy.salesTaxRound(new BigDecimal("99.96")));
		assertEquals(new BigDecimal("14.80"), bookStPolicy.salesTaxRound(new BigDecimal("14.77")));
		assertEquals(new BigDecimal("1.90"), bookStPolicy.salesTaxRound(new BigDecimal("1.88")));
		assertEquals(new BigDecimal("53.00"), bookStPolicy.salesTaxRound(new BigDecimal("53.00")));
		
	}

}
