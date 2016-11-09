package com.lastminute.salestaxes.goods;

import java.math.BigDecimal;

import com.lastminute.salestaxes.policies.DefaultSalesTaxPolicy;

/**
 * 
 * @author Valerio Emanuele
 * This class represent cosmetic goods.
 * 
 *
 */
public class CosmeticItem extends Item
{

	public CosmeticItem(String name, boolean imported, BigDecimal taxFreePrice)
	{
		super(name, imported, taxFreePrice, new DefaultSalesTaxPolicy());
	}

}
