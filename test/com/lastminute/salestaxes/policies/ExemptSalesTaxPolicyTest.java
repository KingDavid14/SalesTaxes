package com.lastminute.salestaxes.policies;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.lastminute.salestaxes.goods.BookItem;
import com.lastminute.salestaxes.goods.FoodItem;
import com.lastminute.salestaxes.goods.MedicalProductItem;
import com.lastminute.salestaxes.policies.ExemptSalesTaxPolicy;

/**
 * 
 * @author Valerio Emanuele
 * This class tests if the ExemptSalesTaxPolicy class calculate correctly the sales tax amount
 * for the basic sales tax exempt goods (currently are books, food and medical products) .
 * 
 * In order to verify if the getSalesTaxAmount method work correctly are compared the hard coded
 * expected sales tax and the item sales tax calculated by the getSalesTaxAmount method
 *
 */
public class ExemptSalesTaxPolicyTest
{
	BookItem importedBook;
	BookItem book;
	FoodItem importedFood;
	FoodItem food;
	MedicalProductItem medicalProduct;
	
	@Before
	public void setUp() throws Exception
	{
		importedBook = new BookItem("imported book", true, new BigDecimal("10.00"));
		book = new BookItem("book", false, new BigDecimal("12.49"));
		food = new FoodItem("chocolate bar", false, new BigDecimal("0.85"));
		importedFood = new FoodItem("box of imported chocolates", true, new BigDecimal("11.25"));
		medicalProduct = new MedicalProductItem("box of imported chocolates", false, new BigDecimal("9.75"));
	}

	@After
	public void tearDown() throws Exception
	{
	}

	@Test
	public void testGetSalesTaxesAmount()
	{
		ExemptSalesTaxPolicy exemptSalesTaxPolicy =  new ExemptSalesTaxPolicy();
		
		assertEquals(new BigDecimal("0.50"), exemptSalesTaxPolicy.getSalesTaxesAmount(importedBook));
		assertEquals(new BigDecimal("0"), exemptSalesTaxPolicy.getSalesTaxesAmount(book));
		
		assertEquals(new BigDecimal("0"), exemptSalesTaxPolicy.getSalesTaxesAmount(food));
		assertEquals(new BigDecimal("0.60"), exemptSalesTaxPolicy.getSalesTaxesAmount(importedFood));
		
		assertEquals(new BigDecimal("0"), exemptSalesTaxPolicy.getSalesTaxesAmount(medicalProduct));
	}

}
