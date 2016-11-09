package com.lastminute.salestaxes;

import java.math.BigDecimal;
import java.util.Locale;

import com.lastminute.salestaxes.ecommerce.Receipt;
import com.lastminute.salestaxes.ecommerce.ShoppingBasket;
import com.lastminute.salestaxes.goods.BookItem;
import com.lastminute.salestaxes.goods.CosmeticItem;
import com.lastminute.salestaxes.goods.FoodItem;
import com.lastminute.salestaxes.goods.MedicalProductItem;
import com.lastminute.salestaxes.goods.MultimediaItem;
import com.lastminute.salestaxes.resources.BundleManager;

public class Main
{

	private static BundleManager bundle = BundleManager.getInstance();
	public static void main(String[] args)
	{
		//Remove the below comment if you want test in italian
		//bundle.setBundleLocale(Locale.ITALY);
		
		basket1();
		basket2();
		basket3();
	}

	protected static void basket1()
	{
		ShoppingBasket basket1 = new ShoppingBasket();
		
		basket1.addItem(new BookItem(bundle.getString("book"), false, new BigDecimal("12.49")));
		basket1.addItem(new MultimediaItem(bundle.getString("music.cd"), false, new BigDecimal("14.99")));
		basket1.addItem(new FoodItem(bundle.getString("chocolate.bar"), false, new BigDecimal("0.85")));
		
		Receipt receipt = basket1.purchase();
		receipt.display();
	}
	
	protected static void basket2()
	{
		ShoppingBasket basket2 = new ShoppingBasket();
		
		basket2.addItem(new CosmeticItem(bundle.getString("bottle.perfume"), true, new BigDecimal("47.50")));
		basket2.addItem(new FoodItem(bundle.getString("box.chocolates"), true, new BigDecimal("10.00")));
		
		Receipt receipt = basket2.purchase();
		receipt.display();
	}
	
	protected static void basket3()
	{
		ShoppingBasket basket3 = new ShoppingBasket();
		
		basket3.addItem(new CosmeticItem(bundle.getString("bottle.perfume"), true, new BigDecimal("27.99")));
		basket3.addItem(new CosmeticItem(bundle.getString("bottle.perfume"), false, new BigDecimal("18.99")));
		basket3.addItem(new MedicalProductItem(bundle.getString("packet.headache.pills"), false, new BigDecimal(9.75)));
		basket3.addItem(new FoodItem(bundle.getString("box.chocolates"), true, new BigDecimal("11.25")));
		
		Receipt receipt = basket3.purchase();
		receipt.display();
	}

}
