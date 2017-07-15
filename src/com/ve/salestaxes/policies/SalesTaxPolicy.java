package com.ve.salestaxes.policies;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.ve.salestaxes.goods.Item;

/**
 * 
 * @author Valerio Emanuele
 * @see https://en.wikipedia.org/wiki/Strategy_pattern
 * This abstract class is the strategy interface according to Strategy Design pattern
 * and declare the method that every item must implement to calculate the amount of sales taxes.
 *  
 * In this case, was used abstract class instead of interface to introduce a centralized validation
 * for the getSalesTaxesAmount method.
 *
 */
public abstract class SalesTaxPolicy
{
	
	/**
	 * Basic sales tax is applicable at a rate of 10% on all goods
	 */
	final static protected BigDecimal BASIC_SALES_TAX_RATE = new BigDecimal("0.1");
	
	/**
	 * Import duty is an additional sales tax applicable on all imported goods at a rate of 5%, with no exemptions.
	 */
	final static protected BigDecimal IMPORT_DUTY_RATE = new BigDecimal("0.05");
	
	/**
	 * The sales taxes must be rounded up to the nearest 0.05
	 */
	private static final BigDecimal ROUNDING_PRECISION = new BigDecimal("0.05");
	
	/**
	 * Calculate the amount of tax rates for the passed item
	 * This method was implemented to make centralized validation of passed item 
	 * to avoid to repeat the code in every concrete policy.
	 * @param item - the item for which to calculate the amount of sales taxes
	 * @return the amount of sales taxes (default is 0 for abstract SalesTaxPolicy)
	 */
	public BigDecimal getSalesTaxesAmount(Item item){
		if (item == null || !item.isValid()){
			throw new IllegalArgumentException("item is null or invalid. Please check item");
		}
		
		return BigDecimal.ZERO;
	}
	
	/**
	 * This method executes the the rounding up to the nearest 0.05
	 * @param salesTax - the amount to round
	 * @return - salesTax roundend up to the nearest 0.05
	 */
	public BigDecimal salesTaxRound(BigDecimal salesTax)
	{
		BigDecimal divided = salesTax.divide(ROUNDING_PRECISION, 0, RoundingMode.UP);
        BigDecimal result = divided.multiply(ROUNDING_PRECISION);
		return result;
	}
}
