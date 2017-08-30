package com.ve.salestaxes.bo;

import java.math.BigDecimal;

import com.ve.salestaxes.services.ExemptSalesTaxPolicyService;

/**
 * 
 * @author Valerio Emanuele
 * This class represents food goods
 */
public class FoodItem extends Item
{

	/**
	 * The FoodItem constructor take in input name, imported and taxFreePrice
	 * The default sales tax policy is the FoodSalesTaxPolicy that in case
	 * can be changed at run time by calling its relative setter method
	 * @param id
	 * @param name
	 * @param imported
	 * @param taxFreePrice
	 */
	public FoodItem(int id, String name, boolean imported, BigDecimal taxFreePrice)
	{
		super(id, name, imported, taxFreePrice, new ExemptSalesTaxPolicyService());
	}

}