package com.ve.salestaxes.bo;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * 
 * @author Valerio Emanuele
 * This class represent the receipt which lists the name of all the items and their price (including tax)
 */
public class Receipt
{

	private Collection<ShoppingItem> purchasedItems;
	private BigDecimal salesTaxes;
	private BigDecimal total;

	public Receipt(Collection<ShoppingItem> shoppingBasket)
	{
		this.purchasedItems = shoppingBasket;
		salesTaxes = BigDecimal.ZERO;
		total = BigDecimal.ZERO;
	}

	public Collection<ShoppingItem> getPurchasedItems() {
		return purchasedItems;
	}

	public void setPurchasedItems(Collection<ShoppingItem> purchasedItems) {
		this.purchasedItems = purchasedItems;
	}

	public BigDecimal getSalesTaxes()
	{
		return salesTaxes;
	}

	public void setSalesTaxes(BigDecimal salesTaxes) {
		this.salesTaxes = salesTaxes;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getTotal()
	{
		return total;
	}
}