package com.ve.salestaxes.bo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.ve.salestaxes.bo.BookItem;
import com.ve.salestaxes.bo.CosmeticItem;
import com.ve.salestaxes.bo.Item;
import com.ve.salestaxes.bo.MultimediaItem;
import com.ve.salestaxes.bo.ShoppingBasket;
import com.ve.salestaxes.bo.ShoppingItem;

/**
 * 
 * @author Valerio Emanuele
 * This class tests if the exposed methods of ShoppingBasket works correctly
 */
public class ShoppingBasketTest
{
	private ShoppingBasket shoppingBasket;
	
	@Rule
    public ExpectedException thrown= ExpectedException.none();
	
	@Before
	public void setUp() throws Exception
	{
		shoppingBasket = new ShoppingBasket();
	}

	@Test
	public void testAddInvalidItem()
	{
		thrown.expect(IllegalArgumentException.class);
		Item item = new BookItem(1, "", true, null);
		shoppingBasket.addItem(item);
	}
	
	@Test
	public void testAddItem()
	{
		Item item = new BookItem(2, "book", true, BigDecimal.ONE);
		shoppingBasket.addItem(item);
		
		assertNotNull(shoppingBasket.getShoppingItem(item));
	}
	
	@Test
	public void testIncrementQuantity()
	{
		Item item = new BookItem(4, "book", true, BigDecimal.ONE);
		shoppingBasket.addItem(item);
		
		ShoppingItem shopItem = shoppingBasket.getShoppingItem(item);
		shoppingBasket.incrementQuantity(item);
		
		assertNotNull(shoppingBasket.getShoppingItem(item));
		assertEquals(2,shoppingBasket.getShoppingItem(item).getQuantity());
		
		//if it's added the same item then the shopping basket increments the quantity
		shoppingBasket.addItem(item);
		assertEquals(3,shoppingBasket.getShoppingItem(item).getQuantity());
	}
	
	@Test
	public void testDecrementInvalidQuantity()
	{
		thrown.expect(IllegalArgumentException.class);
		Item item = new MultimediaItem(1, "Music CD", true, BigDecimal.TEN);
		shoppingBasket.addItem(item);
		
		ShoppingItem shoppingItem = shoppingBasket.getShoppingItem(item);
		shoppingBasket.decrementQuantity(item);
	}
	
	@Test
	public void testDecrementQuantity()
	{
		Item item = new MultimediaItem(1, "Music CD", true, BigDecimal.TEN);
		ShoppingItem shopItem = new ShoppingItem(item, 10);
		shoppingBasket.addItem(shopItem);
		shoppingBasket.decrementQuantity(item);
		
		assertEquals(9,shoppingBasket.getShoppingItem(item).getQuantity());
	}
	
	@Test
	public void testRemoveItem()
	{
		Item item = new CosmeticItem(1, "Parfume", true, BigDecimal.TEN);
		
		shoppingBasket.addItem(item);
		
		assertNotNull(shoppingBasket.getShoppingItem(item));
		
		shoppingBasket.removeShoppingItem(item);
		assertNull(shoppingBasket.getShoppingItem(item));
	}
}
