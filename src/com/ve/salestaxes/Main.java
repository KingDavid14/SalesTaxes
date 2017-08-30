package com.ve.salestaxes;

import com.ve.salestaxes.bo.Receipt;
import com.ve.salestaxes.services.ReceiptService;
import com.ve.salestaxes.services.ShoppingService;

public class Main
{
	private static ShoppingService shoppingService;
	private static ReceiptService receiptService;
	
	public static void main(String[] args)
	{
		shoppingService = new ShoppingService();
		receiptService = new ReceiptService();
		
		String userId = System.getProperty("user.name");
		
		basket1(userId);
		basket2(userId);
		basket3(userId);
	}

	protected static void basket1(String userId)
	{
		shoppingService.addItems(userId, 1,2,3);
		Receipt receipt = shoppingService.purchase(userId);
		receiptService.display(receipt);
	}
	
	protected static void basket2(String userId)
	{
		shoppingService.addItems(userId, 4,5);
		Receipt receipt = shoppingService.purchase(userId);
		receiptService.display(receipt);
	}
	
	protected static void basket3(String userId)
	{
		shoppingService.addItems(userId, 6,7,8,9);
		Receipt receipt = shoppingService.purchase(userId);
		receiptService.display(receipt);
	}
	
}
