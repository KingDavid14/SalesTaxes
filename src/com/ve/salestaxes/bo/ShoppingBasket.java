package com.ve.salestaxes.bo;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

/**
 * 
 * @author Valerio Emanuele
 * @see ShoppingItem
 * This class represents the shopping basket.
 * It allows to add, remove, retrieve and change quantity of ShoppingItem
 */
public class ShoppingBasket
{
	private static final transient Logger log = Logger.getLogger(ShoppingBasket.class);
	
	private ArrayList<ShoppingItem> shoppingBasket;

	public ShoppingBasket()
	{
		super();
		this.shoppingBasket = new ArrayList<>();
	}
	
	public void addItem(Item item){
		if (item == null || !item.isValid()){
			String message = "The item is null or not valid!";
			log.error(message);
			throw new IllegalArgumentException(message);
		}
		
		ShoppingItem addingItem = new ShoppingItem(item);
		addItem(addingItem);
	}
	
	public void addItem(ShoppingItem shoppingItem){
		if (shoppingItem == null || !shoppingItem.getItem().isValid()){
			String message = "The item is null or not valid!";
			log.error(message);
			throw new IllegalArgumentException(message);
		}
		
		if (shoppingBasket.contains(shoppingItem)){
			incrementQuantity(shoppingItem.getItem());
		}
		else{
			shoppingBasket.add(shoppingItem);
		}
	}
	
	/**
	 * Increments by one the quantity of an item already placed in the kart
	 * @param item the item to increment the quantity
	 */
	public void incrementQuantity(Item item){
		incrementQuantity(item, 1);
	}
	
	/**
	 * Decrements by one the quantity of an item already placed in the kart
	 * @param item the item to increment the quantity
	 */
	public void decrementQuantity(Item item){
		incrementQuantity(item, -1);
	}
	
	/**
	 * Update the quantity of an item placed in the shopping basket.
	 * @param item - the item to update the quantity
	 * @param num - the quantity to add to the current quantity
	 */
	private void incrementQuantity(Item item, int num){
		int itemIndex = indexOf(item);
		if (itemIndex == -1){
			String message = "Doesn't exists an item with itemId " + item.getId() + " in the shopping basket";
			log.error(message);
			throw new IllegalArgumentException(message);
		}
		
		ShoppingItem shoppingItem = shoppingBasket.get(itemIndex);
		shoppingItem.setQuantity(shoppingItem.getQuantity() + num);
		shoppingBasket.set(itemIndex, shoppingItem);
	}
	
	/**
	 * Removes an item from the shopping basket
	 * @param shoppingItem - the item to delete
	 * @return true if the item was deleted, false otherwise
	 */
	public boolean removeShoppingItem(Item item){
		int index = indexOf(item);
		
		if(index == -1){
			return false;
		}
		return shoppingBasket.remove(index) != null;
	}
	
	/**
	 * Retrieve and returns an item from the shopping basket
	 * @param item - the item to retrieve
	 * @return the item if it's placed in the shopping cart, null otherwise
	 */
	public ShoppingItem getShoppingItem(Item item){
		int index = indexOf(item);
		if(index == -1){
			return null;
		}
		return shoppingBasket.get(index);
	}
	
	/**
	 * Clear the shopping basket from all the items
	 */
	public void clear(){
		shoppingBasket.clear();
	}

	/**
	 * Search the index of the ShoppingItem inside the shoppingBasket list
	 * @param item - the item to search for the index
	 * @return -1 if the shoppingBasket doesn't contains the item, oterwise 
	 *         the index of the shopping item inside the shoppingBasket list
	 */
	private int indexOf(Item item){
		int index = -1;
		for(int i=0; i < shoppingBasket.size(); i++){
			ShoppingItem si = shoppingBasket.get(i);
			if(si.getItem().equals(item)){
				index = i;
				break;
			}
		}
			
		return index;
	}

	public ArrayList<ShoppingItem> getShoppingBasket() {
		return shoppingBasket;
	}
	
}
