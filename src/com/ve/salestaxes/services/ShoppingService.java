package com.ve.salestaxes.services;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.ve.salestaxes.bo.Receipt;
import com.ve.salestaxes.bo.ShoppingBasket;
import com.ve.salestaxes.bo.ShoppingItem;

/**
 * 
 * @author Valerio Emanuele
 * This class contains the business logic of shopping
 */
public class ShoppingService {
	
	private static final transient Logger log = Logger.getLogger(ShoppingService.class);
	
	
	private ItemService itemService;
	
	private ReceiptService receiptService;
	
	/**
	 * Contains the shopping basket for users
	 * the first integer parameter is an user id
	 * (eg. session id, or actual id column value of a "User" db table, etc. )
	 * because in a real application the shopping basket 
	 * it's associated to an user
	 */
	private HashMap<String, ShoppingBasket> shoppingBaskets;

	public ShoppingService() {
		super();
		shoppingBaskets = new HashMap<>();
		itemService = new ItemService();
		receiptService = new ReceiptService();
	}
	
	public void addItem(String userId, int itemId){
		validateUserId(userId);
		validateItem(itemId);
		
		ShoppingBasket shoppingBasket = shoppingBaskets.get(userId);
		
		if(shoppingBasket == null){
			shoppingBasket = new ShoppingBasket();
		}
		
		shoppingBasket.addItem(itemService.getItem(itemId));
		shoppingBaskets.put(userId, shoppingBasket);
	}	
	
	public void addItems(String userId, Integer... itemIds){
		for(Integer itemId : itemIds){
			addItem(userId, itemId);
		}
	}
	
	public boolean removeItem(String userId, int itemId){
		validateUserId(userId);
		
		ShoppingBasket shoppingBasket = shoppingBaskets.get(userId);
		
		if(shoppingBasket == null){
			throw new IllegalStateException(MessageFormat.format("The basket of the user {0} it's empty!. It's not possible to remove the item {1}", userId, itemId));
		}
		
		shoppingBasket.removeShoppingItem(itemService.getItem(itemId));
		shoppingBaskets.put(userId, shoppingBasket);
		
		return true;
	}
	
	public void incrementQuantity(String userId, int itemId){
		validateUserId(userId);
		validateItem(itemId);
		
		ShoppingBasket shoppingBasket = shoppingBaskets.get(userId);
		
		if(shoppingBasket == null){
			throw new IllegalStateException(MessageFormat.format("The basket of the user {0} it's empty!. It's not possible to increment the item {1}", userId, itemId));
		}
		
		shoppingBasket.incrementQuantity(itemService.getItem(itemId));
		shoppingBaskets.put(userId, shoppingBasket);
	}
	
	public void decrementQuantity(String userId, int itemId){
		validateUserId(userId);
		validateItem(itemId);
		
		ShoppingBasket shoppingBasket = shoppingBaskets.get(userId);
		
		if(shoppingBasket == null){
			throw new IllegalStateException(MessageFormat.format("The basket of the user {0} it's empty!. It's not possible to decrement the item {1}", userId, itemId));
		}
		
		shoppingBasket.decrementQuantity(itemService.getItem(itemId));
		shoppingBaskets.put(userId, shoppingBasket);
	}
	
	/**
	 * This is a figurative method that simulates the purchase of
	 * the items placed in the shopping baskets
	 */
	public Receipt purchase(String userId){
		validateUserId(userId);
		
		ShoppingBasket userShoppingBasket = shoppingBaskets.get(userId);
		if(userShoppingBasket == null){
			throw new IllegalStateException("It's not possible to purchase an empty shopping basket!");
		}
		
		Receipt receipt = receiptService.executePayment((Collection<ShoppingItem>) userShoppingBasket.getShoppingBasket().clone());
		
		
		//clear the shopping basket
		userShoppingBasket.clear();
		shoppingBaskets.put(userId, userShoppingBasket);
		
		return receipt;
	}
	
	public ShoppingBasket getUserShoppingBasket(String userId){
		validateUserId(userId);
		
		return shoppingBaskets.get(userId);
	}
	
	protected void validateUserId(String userId) {
		if (StringUtils.isBlank(userId)){
			String message = "The userId cannot be empty!";
			log.error(message);
			throw new IllegalArgumentException(message);
		}
	}
	
	protected void validateItem(int itemId) {
		if (itemService.getItem(itemId)== null){
			String message = "Does not exsists an item with id "+itemId;
			log.error(message);
			throw new IllegalArgumentException(message);
		}
	}
}
