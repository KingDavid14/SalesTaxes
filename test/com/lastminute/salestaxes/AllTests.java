package com.lastminute.salestaxes;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.lastminute.salestaxes.ecommerce.ReceiptTest;
import com.lastminute.salestaxes.ecommerce.ShoppingBasketTest;
import com.lastminute.salestaxes.goods.ItemTest;
import com.lastminute.salestaxes.policies.DefaultSalesTaxPolicyTest;
import com.lastminute.salestaxes.policies.ExemptSalesTaxPolicyTest;
import com.lastminute.salestaxes.policies.SalesTaxPolicyTest;
import com.lastminute.salestaxes.resources.BundleManagerTest;

@RunWith(Suite.class)
@SuiteClasses({
	ItemTest.class,
	SalesTaxPolicyTest.class,
	ExemptSalesTaxPolicyTest.class,
	DefaultSalesTaxPolicyTest.class,
	ShoppingBasketTest.class,
	ReceiptTest.class,
	BundleManagerTest.class})
public class AllTests
{

}
