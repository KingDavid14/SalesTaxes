package com.ve.salestaxes.bo;

import java.math.BigDecimal;

import org.apache.log4j.Logger;

import com.ve.salestaxes.services.SalesTaxPolicyService;

/**
 * 
 * @author Valerio Emanuele
 * This is the base class for all goods
 */
public abstract class Item
{
	private static final transient Logger log = Logger.getLogger(Item.class);
	protected int id;
	protected String name;
	protected boolean imported;
	protected BigDecimal taxFreePrice;
	protected SalesTaxPolicyService salesTaxPolicyService;
	
	public Item(int id, String name, boolean imported, BigDecimal taxFreePrice)
	{
		super();
		this.id = id;
		this.name = name;
		this.imported = imported;
		this.taxFreePrice = taxFreePrice;
	}
	
	public Item(int id, String name, boolean imported, BigDecimal taxFreePrice, SalesTaxPolicyService salesTaxPolicyService)
	{
		this(id, name, imported, taxFreePrice);
		this.salesTaxPolicyService = salesTaxPolicyService;
	}
	
	/**
	 * This method check if the Item is Valid: name not empty,taxFreePrice not null and salesTaxPolicy
	 * @return true if this item is valid, fals otherwise
	 */
	public boolean isValid()
	{
		return 	name != null 
				&& !name.trim().isEmpty() 
				&& taxFreePrice != null 
				&& salesTaxPolicyService != null;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public boolean isImported()
	{
		return imported;
	}

	public void setImported(boolean imported)
	{
		this.imported = imported;
	}

	public BigDecimal getTaxFreePrice()
	{
		return taxFreePrice;
	}

	public void setTaxFreePrice(BigDecimal taxFreePrice)
	{
		this.taxFreePrice = taxFreePrice;
	}

	public void setSalesTaxPolicy(SalesTaxPolicyService salesTaxPolicy)
	{
		this.salesTaxPolicyService = salesTaxPolicy;
	}
	
	/**
	 * Returns the sales taxes amount for this item
	 */
	public BigDecimal getSalesTaxAmount(){
		return salesTaxPolicyService.getSalesTaxesAmount(this);
	}
	
	/**
	 * Calculate the shelf price: is the tax free price plus the calculated sales tax amount
	 * @return
	 */
	public BigDecimal getShelfPrice()
	{
		if (!isValid()){
			String message = "The item is not correctly initialized! Please verify that price, name and sales tax policy are correctly setted.";
			log.error(message);
			throw new IllegalStateException(message);
		}
		
		return taxFreePrice.add(salesTaxPolicyService.getSalesTaxesAmount(this));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
