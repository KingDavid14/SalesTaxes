package com.ve.salestaxes.bo;

import java.math.BigDecimal;

import com.ve.salestaxes.services.DefaultSalesTaxPolicyService;

/**
 * 
 * @author Valerio Emanuele
 * This class represent multimedia goods.
 * 
 *
 */
public class MultimediaItem extends Item
{

	public MultimediaItem(int id, String name, boolean imported, BigDecimal taxFreePrice)
	{
		super(id, name, imported, taxFreePrice, new DefaultSalesTaxPolicyService());
	}

}
