package com.ve.salestaxes.services;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.ve.salestaxes.bo.Receipt;
import com.ve.salestaxes.bo.ShoppingBasket;

public class ShoppingServiceTest {

	private ShoppingService shoppingService;
	private ItemService itemService;
	private String mainUser;
	
	@Before
	public void setUp() throws Exception {
		shoppingService = new ShoppingService();
		itemService = new ItemService();
		mainUser = System.getProperty("user.name");
	}

	@Test
	public void testAddItem() {
		shoppingService.addItem(mainUser, 1);
		
		ShoppingBasket shoppingBasket = shoppingService.getUserShoppingBasket(mainUser);
		assertNotNull(shoppingBasket);
		assertNotNull(shoppingBasket.getShoppingItem(itemService.getItem(1)));
	}

	@Test
	public void testAddItems() {
		shoppingService.addItems(mainUser, 1,2);
		ShoppingBasket shoppingBasket = shoppingService.getUserShoppingBasket(mainUser);
		assertNotNull(shoppingBasket);
		assertEquals(2L, shoppingBasket.getShoppingBasket().size());
		assertEquals(itemService.getItem(1), shoppingService.getUserShoppingBasket(mainUser).getShoppingItem(itemService.getItem(1)).getItem());
		assertEquals(itemService.getItem(2), shoppingService.getUserShoppingBasket(mainUser).getShoppingItem(itemService.getItem(2)).getItem());
	}

	@Test
	public void testPurchase() {
		shoppingService.addItems(mainUser, 1,2,3);
		Receipt receipt = shoppingService.purchase(mainUser);
		
		assertNotNull(receipt);
	}

}
