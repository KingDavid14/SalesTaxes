package com.ve.salestaxes.resources;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Locale;
import java.util.MissingResourceException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.ve.salestaxes.resources.BundleManager;

public class BundleManagerTest {
	
	private static final String ALL_THE_PARAMETER_MUST_BE_NOT_EMPTY_MSG = "All the parameter must be not empty!";
	private static final String THE_KEY_MUST_BE_NOT_EMPTY_MSG = "The key must be not empty!";

	private static final String IMPORTED_KEY = "imported";
	private static final String TOTAL_KEY = "total";
	
	private BundleManager bundle;
	
	@Rule
    public ExpectedException thrown= ExpectedException.none();
	
	@Before
	public void init(){
		 bundle = BundleManager.getInstance();
	}
	
	@Test
	public void testGetInstance() {
		assertNotNull(bundle);
		assertTrue(bundle instanceof BundleManager);
	}
	
	@Test
	public void testGetStringNullKey() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage(THE_KEY_MUST_BE_NOT_EMPTY_MSG);
		bundle.getString(null);
	}
	
	@Test
	public void testGetStringEmptyKey() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage(THE_KEY_MUST_BE_NOT_EMPTY_MSG);
		bundle.getString("");
	}
	
	@Test
	public void testGetStringNotExistingKey() {
		thrown.expect(MissingResourceException.class);
		bundle.getString("not.exists");
	}

	@Test
	public void testGetStringWithNullParameter() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage(ALL_THE_PARAMETER_MUST_BE_NOT_EMPTY_MSG);
		bundle.getString(TOTAL_KEY, new Object[]{null});
	}
	
	@Test
	public void testGetStringWithOneNullParameter() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage(ALL_THE_PARAMETER_MUST_BE_NOT_EMPTY_MSG);
		bundle.getString(TOTAL_KEY, "1", null);
	}
	
	@Test
	public void testGetStringWithParameter() {
		bundle.setBundleLocale(Locale.US);
		String msg = bundle.getString(TOTAL_KEY, "100");
		assertEquals("Total: 100", msg);
	}
	
	@Test
	public void testChangeBundleForLocale() {
		String defaultMessage = bundle.getString(IMPORTED_KEY);
		bundle.setBundleLocale(Locale.ITALY);
		String afterChangeMessage = bundle.getString(IMPORTED_KEY);
		assertFalse(defaultMessage.equals(afterChangeMessage));
	}

}
