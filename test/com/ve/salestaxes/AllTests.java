package com.ve.salestaxes;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.ve.salestaxes.bo.ItemTest;
import com.ve.salestaxes.bo.ShoppingBasketTest;
import com.ve.salestaxes.resources.BundleManagerTest;
import com.ve.salestaxes.services.DefaultSalesTaxPolicyServiceTest;
import com.ve.salestaxes.services.ExemptSalesTaxPolicyServiceTest;
import com.ve.salestaxes.services.ItemServiceTest;
import com.ve.salestaxes.services.ReceiptServiceTest;
import com.ve.salestaxes.services.SalesTaxPolicyServiceTest;
import com.ve.salestaxes.services.ShoppingServiceTest;

@RunWith(Suite.class)
@SuiteClasses({
	ItemTest.class,
	SalesTaxPolicyServiceTest.class,
	ExemptSalesTaxPolicyServiceTest.class,
	DefaultSalesTaxPolicyServiceTest.class,
	ShoppingBasketTest.class,
	BundleManagerTest.class,
	ShoppingServiceTest.class,
	ItemServiceTest.class,
	ReceiptServiceTest.class})
public class AllTests
{

}
