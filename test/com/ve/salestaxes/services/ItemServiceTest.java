package com.ve.salestaxes.services;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.ve.salestaxes.bo.Item;
import com.ve.salestaxes.bo.MultimediaItem;
import com.ve.salestaxes.resources.BundleManager;

public class ItemServiceTest {

	private ItemService itemService;
	private Item dummyItem;
	private int dummyItemId;
	private static final int BOOK_ITEM_ID = 1;
	private static BundleManager bundle = BundleManager.getInstance();
	
	@Before
	public void setUp() throws Exception {
		itemService = new ItemService();
		dummyItemId = 99;
		dummyItem = new MultimediaItem(dummyItemId, "dummyItem", false, BigDecimal.TEN);
	}

	@Test
	public void testGetItem() {
		assertNull(itemService.getItem(dummyItemId));
		assertNotNull(itemService.getItem(BOOK_ITEM_ID));
	}
	
	@Test
	public void testRemoveItem() {
		Item item = itemService.getItem(BOOK_ITEM_ID);
		assertNotNull(item);
		itemService.removeItem(item);
		assertNull(itemService.getItem(BOOK_ITEM_ID));
	}
	
	@Test
	public void testAddItem() {
		assertNull(itemService.getItem(dummyItemId));
		itemService.addItem(dummyItem);
		assertNotNull(itemService.getItem(dummyItemId));
		itemService.removeItem(dummyItem);
	}

	@Test
	public void testUpdateItem() {
		Item item = itemService.getItem(BOOK_ITEM_ID);
		assertEquals(bundle.getString("book"), item.getName());
		
		item.setName("newName");
		itemService.updateItem(item);
		item = itemService.getItem(BOOK_ITEM_ID);
		
		assertEquals("newName", item.getName());
	}

	

}
