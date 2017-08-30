package com.ve.salestaxes.services;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Before;
import org.junit.Test;

import com.ve.salestaxes.bo.BookItem;
import com.ve.salestaxes.bo.CosmeticItem;
import com.ve.salestaxes.bo.FoodItem;
import com.ve.salestaxes.bo.MedicalProductItem;
import com.ve.salestaxes.bo.MultimediaItem;
import com.ve.salestaxes.bo.Receipt;
import com.ve.salestaxes.bo.ShoppingBasket;

public class ReceiptServiceTest {

	private ReceiptService receiptService;
	
	private ShoppingBasket basket1;
	private ShoppingBasket basket2;
	private ShoppingBasket basket3;
	
	@Before
	public void setUp() throws Exception {
		receiptService = new ReceiptService();
		
		//init and fill basket 1
		basket1 = new ShoppingBasket();
		
		BookItem book = new BookItem(1, "book", false, new BigDecimal("12.49"));
		MultimediaItem musicCd = new MultimediaItem(2, "music CD", false, new BigDecimal("14.99"));
		FoodItem chocolateBar = new FoodItem(3, "chocolate bar", false, new BigDecimal("0.85"));
		
		basket1.addItem(book);
		basket1.addItem(musicCd);
		basket1.addItem(chocolateBar);
		
		//init and fill basket 2
		basket2 = new ShoppingBasket();
		CosmeticItem bottleOfParfum = new CosmeticItem(4, "bottle of perfume", true, new BigDecimal("47.50"));
		chocolateBar = new FoodItem(5, "box of chocolates", true, new BigDecimal("10.00"));
		
		basket2.addItem(bottleOfParfum);
		basket2.addItem(chocolateBar);
		
		//init and fille basket 3
		basket3 = new ShoppingBasket();
		bottleOfParfum = new CosmeticItem(6, "bottle of perfume", true, new BigDecimal("27.99"));
		CosmeticItem bottleOfParfum2 = new CosmeticItem(7, "bottle of perfume", false, new BigDecimal("18.99"));
		MedicalProductItem pills = new MedicalProductItem(8, "packet of headache pills", false, new BigDecimal(9.75));
		chocolateBar = new FoodItem(9, "box of chocolates", true, new BigDecimal("11.25"));
		
		basket3.addItem(bottleOfParfum);
		basket3.addItem(bottleOfParfum2);
		basket3.addItem(pills);
		basket3.addItem(chocolateBar);
	}

	@Test
	public void testExecutePaymentBasket1() {
		Receipt receipt = receiptService.executePayment(basket1.getShoppingBasket());
		
		BigDecimal expectedTotal = new BigDecimal(29.83);
		BigDecimal expectedSalesTaxes = new BigDecimal(1.50);
		
		expectedTotal = expectedTotal.setScale(2, RoundingMode.HALF_DOWN);
		expectedSalesTaxes = expectedSalesTaxes.setScale(2, RoundingMode.HALF_DOWN);
		
		assertEquals(expectedTotal, receipt.getTotal());
		assertEquals(expectedSalesTaxes, receipt.getSalesTaxes());
	}
	
	@Test
	public void testExecutePaymentBasket2() {
		Receipt receipt = receiptService.executePayment(basket2.getShoppingBasket());
		
		BigDecimal expectedTotal = new BigDecimal(65.15);
		BigDecimal expectedSalesTaxes = new BigDecimal(7.65);
		
		expectedTotal = expectedTotal.setScale(2, RoundingMode.HALF_DOWN);
		expectedSalesTaxes = expectedSalesTaxes.setScale(2, RoundingMode.HALF_DOWN);
		
		assertEquals(expectedTotal, receipt.getTotal());
		assertEquals(expectedSalesTaxes, receipt.getSalesTaxes());
	}
	
	@Test
	public void testExecutePaymentBasket3() {
		Receipt receipt = receiptService.executePayment(basket3.getShoppingBasket());
		
		BigDecimal expectedTotal = new BigDecimal(74.68);
		BigDecimal expectedSalesTaxes = new BigDecimal(6.70);
		
		expectedTotal = expectedTotal.setScale(2, RoundingMode.HALF_DOWN);
		expectedSalesTaxes = expectedSalesTaxes.setScale(2, RoundingMode.HALF_DOWN);
		
		assertEquals(expectedTotal, receipt.getTotal());
		assertEquals(expectedSalesTaxes, receipt.getSalesTaxes());
	}

}
