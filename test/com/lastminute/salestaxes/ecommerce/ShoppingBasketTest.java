package com.lastminute.salestaxes.ecommerce;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.lastminute.salestaxes.ecommerce.ShoppingBasket;
import com.lastminute.salestaxes.ecommerce.ShoppingItem;
import com.lastminute.salestaxes.goods.BookItem;
import com.lastminute.salestaxes.goods.CosmeticItem;
import com.lastminute.salestaxes.goods.Item;
import com.lastminute.salestaxes.goods.MultimediaItem;

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
		Item item = new BookItem("", true, null);
		shoppingBasket.addItem(item);
	}
	
	@Test
	public void testAddItem()
	{
		Item item = new BookItem("book", true, BigDecimal.ONE);
		shoppingBasket.addItem(item);
		
		assertNotNull(shoppingBasket.getItem(item.hashCode()));
	}
	
	@Test
	public void testIncrementQuantityUnexistentItem()
	{
		thrown.expect(IllegalArgumentException.class);
		Item item = new MultimediaItem("Music CD", true, BigDecimal.TEN);
		shoppingBasket.addItem(item);
		shoppingBasket.incrementQuantity(123);
	}
	
	@Test
	public void testIncrementQuantity()
	{
		Item item = new BookItem("book", true, BigDecimal.ONE);
		shoppingBasket.addItem(item);
		
		ShoppingItem shopItem = shoppingBasket.getItem(item.hashCode());
		shoppingBasket.incrementQuantity(shopItem.getKey());
		
		assertNotNull(shoppingBasket.getItem(shopItem.getKey()));
		assertEquals(2,shoppingBasket.getItem(shopItem.getKey()).getQuantity());
		
		//if it's added the same item then the shopping basket increments the quantity
		shoppingBasket.addItem(item);
		assertEquals(3,shoppingBasket.getItem(shopItem.getKey()).getQuantity());
	}
	
	@Test
	public void testDecrementInvalidQuantity()
	{
		thrown.expect(IllegalArgumentException.class);
		Item item = new MultimediaItem("Music CD", true, BigDecimal.TEN);
		shoppingBasket.addItem(item);
		shoppingBasket.decrementQuantity(item.hashCode());
	}
	
	@Test
	public void testDecrementQuantity()
	{
		Item item = new MultimediaItem("Music CD", true, BigDecimal.TEN);
		ShoppingItem shopItem = new ShoppingItem(item, 10);
		shoppingBasket.addItem(shopItem);
		shoppingBasket.decrementQuantity(shopItem.getKey());
		
		assertEquals(9,shoppingBasket.getItem(shopItem.getKey()).getQuantity());
	}
	
	@Test
	public void testRemoveItem()
	{
		Item item = new CosmeticItem("Parfume", true, BigDecimal.TEN);
		
		shoppingBasket.addItem(item);
		
		assertNotNull(shoppingBasket.getItem(item.hashCode()));
		
		shoppingBasket.removeItem(item.hashCode());
		assertNull(shoppingBasket.getItem(item.hashCode()));
	}

	@Test
	public void testPurchaseEmptyShoppingBasket(){
		thrown.expect(IllegalStateException.class);
		shoppingBasket.clear();
		shoppingBasket.purchase();
	}
	
	@Test
	public void testPurchaseShoppingBasket(){
		Item item = new CosmeticItem("Parfume", true, BigDecimal.TEN);
		shoppingBasket.addItem(item);
		
		assertNotNull(shoppingBasket.purchase());
	}
}
