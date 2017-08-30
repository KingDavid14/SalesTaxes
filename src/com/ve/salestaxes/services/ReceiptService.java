package com.ve.salestaxes.services;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Collection;

import com.ve.salestaxes.bo.Receipt;
import com.ve.salestaxes.bo.ShoppingItem;
import com.ve.salestaxes.resources.BundleManager;

public class ReceiptService {
	
	private static BundleManager messages = BundleManager.getInstance();
	private final static DecimalFormat CUSTOM_DECIMAL_FORMAT;
	
	static{
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setDecimalSeparator(messages.getString("decimal.separator").charAt(0));
		CUSTOM_DECIMAL_FORMAT = new DecimalFormat(messages.getString("decimal.format"), symbols);
	}

	public Receipt executePayment(Collection<ShoppingItem> userShoppingBasket){
		//executes payment...
		
		Receipt receipt = new Receipt(userShoppingBasket);
		
		for (ShoppingItem item : receipt.getPurchasedItems()){
			receipt.setSalesTaxes(receipt.getSalesTaxes().add(item.getSalesTax()));
			receipt.setTotal(receipt.getTotal().add(item.getShelfPrice()));
		}
		
		return receipt;
	}
	
	public void display(Receipt receipt){
		displayItems(receipt);
		displaySalesTax(receipt);
		displayTotal(receipt);
	}
	
	private void displayItems(Receipt receipt)
	{
		for (ShoppingItem item : receipt.getPurchasedItems()){
			System.out.print(item.getQuantity()+"\t");
			if (item.getItem().isImported()){
				System.out.print(messages.getString("imported"));
			}
			System.out.print(item.getItem().getName()+":\t");
			System.out.print(CUSTOM_DECIMAL_FORMAT.format(item.getShelfPrice())+"\n");
		}
		
	}
	
	private void displaySalesTax(Receipt receipt)
	{	
		System.out.println(messages.getString("sales.taxes", CUSTOM_DECIMAL_FORMAT.format(receipt.getSalesTaxes())));
	}
	
	private void displayTotal(Receipt receipt)
	{
		System.out.println(messages.getString("total", CUSTOM_DECIMAL_FORMAT.format(receipt.getTotal()))+"\n\n\n");
	}
}
