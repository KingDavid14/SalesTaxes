package com.ve.salestaxes.ecommerce;

import java.util.HashMap;

import org.apache.log4j.Logger;

import com.ve.salestaxes.goods.Item;

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
	
	private HashMap<Integer, ShoppingItem> shoppingBasket;

	public ShoppingBasket()
	{
		super();
		this.shoppingBasket = new HashMap<Integer, ShoppingItem>();
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
	
	public void addItem(ShoppingItem item){
		if (item == null || !item.getItem().isValid()){
			String message = "The item is null or not valid!";
			log.error(message);
			throw new IllegalArgumentException(message);
		}
		
		if (shoppingBasket.containsKey(item.getKey())){
			incrementQuantity(item.getKey());
		}
		else{
			shoppingBasket.put(item.getKey(), item);
		}
	}
	
	/**
	 * Increments by one the quantity of an item already placed in the kart
	 * @param key the key of the item to increment the quantity
	 */
	public void incrementQuantity(int key){
		incrementQuantity(key, 1);
	}
	
	/**
	 * Decrements by one the quantity of an item already placed in the kart
	 * @param key the key of the item to decrement the quantity
	 */
	public void decrementQuantity(int key){
		incrementQuantity(key, -1);
	}
	
	/**
	 * Update the quantity of an item placed in the shopping basket.
	 * @param key - the key of the item to update the quantity
	 * @param num - the quantity to add to the current quantity
	 */
	private void incrementQuantity(int key, int num){
		ShoppingItem item = shoppingBasket.get(key);
		if (item == null){
			String message = "Doesn't exists an item with key " + key + " in the shopping basket";
			log.error(message);
			throw new IllegalArgumentException(message);
		}
		
		item.setQuantity(item.getQuantity() + num);
		shoppingBasket.put(key, item);
	}
	
	/**
	 * Removes an item from the shopping basket
	 * @param key - the key of the item to delete
	 * @return true if the item was deleted, false otherwise
	 */
	public boolean removeItem(int key){
		return shoppingBasket.remove(key) != null;
	}
	
	/**
	 * Retrieve and returns an item from the shopping basket
	 * @param key - the key of the item to retrieve
	 * @return the item if it's placed in the shopping cart, null otherwise
	 */
	public ShoppingItem getItem(int key){
		return shoppingBasket.get(key);
	}
	
	/**
	 * Clear the shopping basket from all the items
	 */
	public void clear(){
		shoppingBasket.clear();
	}
	
	/**
	 * This is a figurative method that simulates the purchase of
	 * the items placed in the shopping baskets
	 */
	public Receipt purchase(){
		
		if(shoppingBasket.isEmpty()){
			throw new IllegalStateException("It's not possible to purchase an empty shopping basket!");
		}
		
		//TODO: execute payment
		
		return new Receipt(shoppingBasket);
	}
}
