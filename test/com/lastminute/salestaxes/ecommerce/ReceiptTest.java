package com.lastminute.salestaxes.ecommerce;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.lastminute.salestaxes.goods.BookItem;
import com.lastminute.salestaxes.goods.CosmeticItem;
import com.lastminute.salestaxes.goods.FoodItem;
import com.lastminute.salestaxes.goods.MedicalProductItem;
import com.lastminute.salestaxes.goods.MultimediaItem;


/**
 * 
 * @author Valerio Emanuele
 * This class checks if the Receipt class calculate correctly
 * the total sales taxes and the total of the items in
 * the shopping basket
 *
 */
public class ReceiptTest
{
	ShoppingBasket basket1;
	ShoppingBasket basket2;
	ShoppingBasket basket3;

	@Before
	public void setUp() throws Exception
	{
		//init and fill basket 1
		basket1 = new ShoppingBasket();
		
		BookItem book = new BookItem("book", false, new BigDecimal("12.49"));
		MultimediaItem musicCd = new MultimediaItem("music CD", false, new BigDecimal("14.99"));
		FoodItem chocolateBar = new FoodItem("chocolate bar", false, new BigDecimal("0.85"));
		
		basket1.addItem(book);
		basket1.addItem(musicCd);
		basket1.addItem(chocolateBar);
		
		//init and fill basket 2
		basket2 = new ShoppingBasket();
		CosmeticItem bottleOfParfum = new CosmeticItem("bottle of perfume", true, new BigDecimal("47.50"));
		chocolateBar = new FoodItem("box of chocolates", true, new BigDecimal("10.00"));
		
		basket2.addItem(bottleOfParfum);
		basket2.addItem(chocolateBar);
		
		//init and fille basket 3
		basket3 = new ShoppingBasket();
		bottleOfParfum = new CosmeticItem("bottle of perfume", true, new BigDecimal("27.99"));
		CosmeticItem bottleOfParfum2 = new CosmeticItem("bottle of perfume", false, new BigDecimal("18.99"));
		MedicalProductItem pills = new MedicalProductItem("packet of headache pills", false, new BigDecimal(9.75));
		chocolateBar = new FoodItem("box of chocolates", true, new BigDecimal("11.25"));
		
		basket3.addItem(bottleOfParfum);
		basket3.addItem(bottleOfParfum2);
		basket3.addItem(pills);
		basket3.addItem(chocolateBar);
	}

	@Test
	public void testGetSalesTaxes()
	{
		assertEquals(new BigDecimal("1.50"), basket1.purchase().getSalesTaxes());
		assertEquals(new BigDecimal("7.65"), basket2.purchase().getSalesTaxes());
		assertEquals(new BigDecimal("6.70"), basket3.purchase().getSalesTaxes());
	}

	@Test
	public void testGetTotal()
	{
		assertEquals(new BigDecimal("29.83"), basket1.purchase().getTotal());
		assertEquals(new BigDecimal("65.15"), basket2.purchase().getTotal());
		assertEquals(new BigDecimal("74.68"), basket3.purchase().getTotal());
	}

}
