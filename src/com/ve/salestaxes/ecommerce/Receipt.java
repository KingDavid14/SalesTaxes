package com.ve.salestaxes.ecommerce;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Collection;
import java.util.HashMap;

import com.ve.salestaxes.resources.BundleManager;

/**
 * 
 * @author Valerio Emanuele
 * This class represent the receipt which lists the name of all the items and their price (including tax)
 */
public class Receipt
{

	private Collection<ShoppingItem> shoppingBasket;
	private BigDecimal salesTaxes;
	private BigDecimal total;
	private final DecimalFormat CUSTOM_DECIMAL_FORMAT;
	
	
	private static BundleManager messages = BundleManager.getInstance();
	
	public Receipt(HashMap<Integer, ShoppingItem> shoppingBasket)
	{
		this.shoppingBasket = shoppingBasket.values();
		calculateTaxesAndTotal();
		
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setDecimalSeparator('.');
		CUSTOM_DECIMAL_FORMAT = new DecimalFormat("##0.00", symbols);
	}
	
	public void display(){
		displayItems();
		displaySalesTax();
		displayTotal();
	}
	
	private void calculateTaxesAndTotal(){
		//resets the counters
		salesTaxes = BigDecimal.ZERO;
		total = BigDecimal.ZERO;
		for (ShoppingItem item : shoppingBasket){
			salesTaxes = salesTaxes.add(item.getSalesTax());
			total = total.add(item.getShelfPrice());
		}
	}

	private void displayItems()
	{
		for (ShoppingItem item : shoppingBasket){
			System.out.print(item.getQuantity()+"\t");
			if (item.getItem().isImported()){
				System.out.print(messages.getString("imported"));
			}
			System.out.print(item.getItem().getName()+":\t");
			System.out.print(CUSTOM_DECIMAL_FORMAT.format(item.getShelfPrice())+"\n");
		}
		
	}
	
	private void displaySalesTax()
	{	
		System.out.println(messages.getString("sales.taxes", CUSTOM_DECIMAL_FORMAT.format(salesTaxes)));
	}
	
	private void displayTotal()
	{
		System.out.println(messages.getString("total", CUSTOM_DECIMAL_FORMAT.format(total))+"\n\n\n");
	}

	public BigDecimal getSalesTaxes()
	{
		return salesTaxes;
	}

	public BigDecimal getTotal()
	{
		return total;
	}
}