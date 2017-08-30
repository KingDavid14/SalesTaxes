package com.ve.salestaxes.bo;

import java.math.BigDecimal;

import com.ve.salestaxes.services.DefaultSalesTaxPolicyService;

/**
 * 
 * @author Valerio Emanuele
 * This class represent cosmetic goods.
 * 
 *
 */
public class CosmeticItem extends Item
{

	public CosmeticItem(int id, String name, boolean imported, BigDecimal taxFreePrice)
	{
		super(id, name, imported, taxFreePrice, new DefaultSalesTaxPolicyService());
	}

}
