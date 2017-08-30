package com.ve.salestaxes.policies;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.ve.salestaxes.goods.CosmeticItem;
import com.ve.salestaxes.goods.MultimediaItem;
import com.ve.salestaxes.policies.DefaultSalesTaxPolicy;

/**
 * 
 * @author Valerio Emanuele
 * This class tests if the DefaultSalesTaxPolicy class calculate correctly the sales tax amount
 * for the goods subject to basic sales tax.
 * 
 * In order to verify if the getSalesTaxAmount method work correctly are compared the hard coded
 * expected sales tax and the item sales tax calculated by the getSalesTaxAmount method
 *
 */
public class DefaultSalesTaxPolicyTest
{
	MultimediaItem musicCd;
	CosmeticItem parfumeBottle;
	CosmeticItem importedParfumeBottle;
	
	@Before
	public void setUp() throws Exception
	{
		musicCd = new MultimediaItem("music CD", false, new BigDecimal("14.99"));
		parfumeBottle = new CosmeticItem("parfume bottle", false, new BigDecimal("18.99"));
		importedParfumeBottle = new CosmeticItem("imported parfume bottle", true, new BigDecimal("27.99"));
	}


	@Test
	public void testGetSalesTaxesAmount()
	{
		DefaultSalesTaxPolicy defaultSalesTaxPolicy = new DefaultSalesTaxPolicy();
		
		assertEquals(new BigDecimal("1.50"), defaultSalesTaxPolicy.getSalesTaxesAmount(musicCd));
		assertEquals(new BigDecimal("1.90"), defaultSalesTaxPolicy.getSalesTaxesAmount(parfumeBottle));
		assertEquals(new BigDecimal("4.20"), defaultSalesTaxPolicy.getSalesTaxesAmount(importedParfumeBottle));
	}

}
