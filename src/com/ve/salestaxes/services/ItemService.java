package com.ve.salestaxes.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.ve.salestaxes.bo.BookItem;
import com.ve.salestaxes.bo.CosmeticItem;
import com.ve.salestaxes.bo.FoodItem;
import com.ve.salestaxes.bo.Item;
import com.ve.salestaxes.bo.MedicalProductItem;
import com.ve.salestaxes.bo.MultimediaItem;
import com.ve.salestaxes.resources.BundleManager;

/**
 * 
 * @author Valerio Emanuele
 *	This service manages the items
 */
public class ItemService {
	
	private static BundleManager bundle = BundleManager.getInstance();
	private static List<Item> items;
	
	static{
		items = new ArrayList<>();
		items.add(new BookItem(1, bundle.getString("book"), false, new BigDecimal("12.49")));
		items.add(new MultimediaItem(2, bundle.getString("music.cd"), false, new BigDecimal("14.99")));
		items.add(new FoodItem(3, bundle.getString("chocolate.bar"), false, new BigDecimal("0.85")));
		items.add(new CosmeticItem(4, bundle.getString("bottle.perfume"), true, new BigDecimal("47.50")));
		items.add(new FoodItem(5, bundle.getString("box.chocolates"), true, new BigDecimal("10.00")));
		items.add(new CosmeticItem(6, bundle.getString("bottle.perfume"), true, new BigDecimal("27.99")));
		items.add(new CosmeticItem(7, bundle.getString("bottle.perfume"), false, new BigDecimal("18.99")));
		items.add(new MedicalProductItem(8, bundle.getString("packet.headache.pills"), false, new BigDecimal(9.75)));
		items.add(new FoodItem(9, bundle.getString("box.chocolates"), true, new BigDecimal("11.25")));
	}

	public ItemService() {
		super();
	}

	public void addItem(Item item){
		if(items.contains(item)){
			updateItem(item);
		}
		items.add(item);
	}
	
	/**
	 * Returns an Item with the passed itemId
	 * @param itemId - the id of the item to retrieve
	 * @return the Item object if founded, null otherwise
	 */
	public Item getItem(int itemId){
		for(Item item : items){
			if(item.getId() == itemId){
				return item;
			}
		}
		
		return null;
	}
	
	public void updateItem(Item item){
		int index = items.indexOf(item);
		
		if(index == -1){
			addItem(item);
		}
		else{
			items.set(index, item);
		}
	}
	
	public boolean removeItem(Item item){
		return items.remove(item);
	}
}
