package com.ve.salestaxes.bo;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;

import com.ve.salestaxes.bo.BookItem;

/**
 * 
 * @author Valerio Emanuele
 * This class tests if the isValid methods works correctly 
 * (an Item is valid if name not empty, taxFreePrice not null and salesTaxPolicy not null)
 * 
 * Because is not possible instantiate abstract class Item, in order to test the isValid method
 * are instantiated BookItem objects
 */
public class ItemTest
{

	@Test
	public void testIsValid()
	{
		BookItem invalidBookName = new BookItem(1, null, true, BigDecimal.TEN);
		BookItem invalidBookName2 = new BookItem(2, "", true, BigDecimal.TEN);
		BookItem invalidBookName3 = new BookItem(3, "     ", true, BigDecimal.TEN);
		BookItem invalidBookPrice = new BookItem(4, "book name", true, null);
		BookItem invalidBookNameAndPrice = new BookItem(5, null, true, null);
		BookItem invalidBookNameAndPrice2 = new BookItem(6, "", true, null);
		BookItem invalidBookNameAndPrice3 = new BookItem(7, "     ", true, null);
		
		BookItem validBook = new BookItem(8, "scrum", true, BigDecimal.TEN);
		BookItem validBook2 = new BookItem(9, "lean startup", false, BigDecimal.TEN);
		
		assertFalse(invalidBookName.isValid());
		assertFalse(invalidBookName2.isValid());
		assertFalse(invalidBookName3.isValid());
		assertFalse(invalidBookPrice.isValid());
		assertFalse(invalidBookNameAndPrice.isValid());
		assertFalse(invalidBookNameAndPrice2.isValid());
		assertFalse(invalidBookNameAndPrice3.isValid());
		
		assertTrue(validBook.isValid());
		assertTrue(validBook2.isValid());
	}

}
