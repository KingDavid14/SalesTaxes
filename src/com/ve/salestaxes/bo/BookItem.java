package com.ve.salestaxes.bo;

import java.math.BigDecimal;

import com.ve.salestaxes.services.ExemptSalesTaxPolicyService;

/**
 * 
 * @author Valerio Emanuele
 * This class represents book goods
 */
public class BookItem extends Item
{
	/**
	 * The BookItem constructor take in input name, imported and taxFreePrice
	 * The default sales tax policy is the BookSalesTaxPolicy that in case
	 * can be changed at run time by calling its relative setter method
	 * @param id
	 * @param name
	 * @param imported
	 * @param taxFreePrice
	 */
	public BookItem(int id, String name, boolean imported, BigDecimal taxFreePrice)
	{
		super(id, name, imported, taxFreePrice, new ExemptSalesTaxPolicyService());
		//Note: if the sales tax policy for this kind of item were to change 
		//then probably it should be implemented an ad hoc policy instead of ExemptSalesTaxPolicy
	}
}
