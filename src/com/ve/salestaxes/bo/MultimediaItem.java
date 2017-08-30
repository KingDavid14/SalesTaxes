package com.ve.salestaxes.goods;

import java.math.BigDecimal;

import com.ve.salestaxes.policies.DefaultSalesTaxPolicy;

/**
 * 
 * @author Valerio Emanuele
 * This class represent multimedia goods.
 * 
 *
 */
public class MultimediaItem extends Item
{

	public MultimediaItem(String name, boolean imported, BigDecimal taxFreePrice)
	{
		super(name, imported, taxFreePrice, new DefaultSalesTaxPolicy());
	}

}
