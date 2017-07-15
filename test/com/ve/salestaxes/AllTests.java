package com.ve.salestaxes;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.ve.salestaxes.ecommerce.ReceiptTest;
import com.ve.salestaxes.ecommerce.ShoppingBasketTest;
import com.ve.salestaxes.goods.ItemTest;
import com.ve.salestaxes.policies.DefaultSalesTaxPolicyTest;
import com.ve.salestaxes.policies.ExemptSalesTaxPolicyTest;
import com.ve.salestaxes.policies.SalesTaxPolicyTest;
import com.ve.salestaxes.resources.BundleManagerTest;

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
