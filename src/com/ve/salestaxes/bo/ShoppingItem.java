package com.ve.salestaxes.bo;

import java.math.BigDecimal;

import org.apache.log4j.Logger;

/**
 * 
 * @author Valerio Emanuele
 * @see Item
 * This class describe an item that can be added to Shopping Basket.
 * It wraps an Item and add a quantity field to take count 
 * of the numbers of the same items placed in the shopping basket.
 */
public class ShoppingItem
{
	private static final transient Logger log = Logger.getLogger(ShoppingItem.class);
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
			String message = "quantity can't be less than 1";
			log.error(message);
			throw new IllegalArgumentException(message);
		}
		this.quantity = quantity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((item == null) ? 0 : item.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShoppingItem other = (ShoppingItem) obj;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
			return false;
		return true;
	}	
}
