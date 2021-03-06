package com.ve.salestaxes.services;

import java.math.BigDecimal;

import com.ve.salestaxes.bo.Item;

/**
 * 
 * @author Valerio Emanuele
 * This class implements the policy for the exempt goods from basic sales tax
 */
public class ExemptSalesTaxPolicyService extends SalesTaxPolicyService
{
	/**
	 * For the book goods there is only import duty as sales tax when the books are imported
	 * @return the import duty amount if the the book is imported, 0 otherwise
	 */
	@Override 
	public BigDecimal getSalesTaxesAmount(Item item)
	{
		if (item.isImported()){
			return salesTaxRound(item.getTaxFreePrice().multiply(IMPORT_DUTY_RATE));
		}
		
		return BigDecimal.ZERO;
	}
}