package com.lastminute.salestaxes.goods;

import java.math.BigDecimal;

import org.apache.log4j.Logger;

import com.lastminute.salestaxes.policies.SalesTaxPolicy;

/**
 * 
 * @author Valerio Emanuele
 * This is the base class for all goods
 */
public abstract class Item
{
	private static final transient Logger log = Logger.getLogger(Item.class);
	protected String name;
	protected boolean imported;
	protected BigDecimal taxFreePrice;
	protected SalesTaxPolicy salesTaxPolicy;
	
	public Item(String name, boolean imported, BigDecimal taxFreePrice)
	{
		super();
		this.name = name;
		this.imported = imported;
		this.taxFreePrice = taxFreePrice;
	}
	
	public Item(String name, boolean imported, BigDecimal taxFreePrice, SalesTaxPolicy salesTaxPolicy)
	{
		this(name, imported, taxFreePrice);
		this.salesTaxPolicy = salesTaxPolicy;
	}
	
	/**
	 * This method check if the Item is Valid: name not empty,taxFreePrice not null and salesTaxPolicy
	 * @return true if this item is valid, fals otherwis
	 */
	public boolean isValid()
	{
		return 	name != null 
				&& !name.trim().isEmpty() 
				&& taxFreePrice != null 
				&& salesTaxPolicy != null;
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

	public void setSalesTaxPolicy(SalesTaxPolicy salesTaxPolicy)
	{
		this.salesTaxPolicy = salesTaxPolicy;
	}
	
	/**
	 * Returns the sales taxes amount for this item
	 */
	public BigDecimal getSalesTaxAmount(){
		return salesTaxPolicy.getSalesTaxesAmount(this);
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
		
		return taxFreePrice.add(salesTaxPolicy.getSalesTaxesAmount(this));
	}

	@Override
	/**
	 * Univocal identifier of the item
	 */
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + (imported ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((taxFreePrice == null) ? 0 : taxFreePrice.hashCode());
		return result;
	}

	@Override
	/**
	 * For this case study an item is considered equal to another one
	 * if tha name, the tax free price and the imported flag are equals
	 */
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (imported != other.imported)
			return false;
		if (name == null)
		{
			if (other.name != null)
				return false;
		}
		else if (!name.equals(other.name))
			return false;
		if (taxFreePrice == null)
		{
			if (other.taxFreePrice != null)
				return false;
		}
		else if (!taxFreePrice.equals(other.taxFreePrice))
			return false;
		return true;
	}
	
	
}
