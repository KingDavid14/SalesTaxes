package com.ve.salestaxes.policies;

import java.math.BigDecimal;

import com.ve.salestaxes.goods.Item;

/**
 * 
 * @author Valerio Emanuele
 * This class represents the default sales tax policy or 
 * it calculate the basic sales tax and in case the import duty
 *
 */
public class DefaultSalesTaxPolicy extends SalesTaxPolicy
{
	/**
	 * For goods different from book, food and medical products there is 
	 * the basic sales tax and in case the import duty sales tax
	 * @return the basic sales tax added (in case) to the import duty for the passed Item
	 * @param item: item for which to calculate the sales taxes
	 */
	@Override 
	public BigDecimal getSalesTaxesAmount(Item item)
	{
		BigDecimal salesTax = salesTaxRound(item.getTaxFreePrice().multiply(BASIC_SALES_TAX_RATE));
		if (item.isImported()){
			salesTax = salesTax.add(salesTaxRound(item.getTaxFreePrice().multiply(IMPORT_DUTY_RATE)));
		}
		
		return salesTax;
	}
}
