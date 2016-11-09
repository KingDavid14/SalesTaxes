package com.lastminute.salestaxes.ecommerce;

import java.math.BigDecimal;

import com.lastminute.salestaxes.goods.Item;

/**
 * 
 * @author Valerio Emanuele
 * @see Item
 * This class describe an item that can be added to Shopping Basket.
 * It wraps an ch.alten.salestaxes.goods.Item and add a quantity field to take count 
 * of the numbers of the same items placed in the shopping basket.
 */
public class ShoppingItem
{
	private Item item;
	private int quantity;
	
	public ShoppingItem(Item item, int quantity)
	{
		super();
		this.item = item;
		this.quantity = quantity;
	}
	
	public ShoppingItem(Item item)
	{
		this(item, 1);
	}

	public int getKey(){
		return item.hashCode();
	}
	
	public Item getItem()
	{
		return item;
	}
	
	public BigDecimal getShelfPrice(){
		return item.getShelfPrice().multiply(new BigDecimal(quantity));
	}
	
	public BigDecimal getSalesTax(){
		return item.getSalesTaxAmount().multiply(new BigDecimal(quantity));
	}

	public void setItem(Item item)
	{
		this.item = item;
	}

	public int getQuantity()
	{
		return quantity;
	}

	public void setQuantity(int quantity)
	{
		//the quantity can't be < 1
		if (quantity < 1){
			throw new IllegalArgumentException("quantity can't be less than 1");
		}
		this.quantity = quantity;
	}
	
}
