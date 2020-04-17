package com.kadir.inventory.model;

import org.springframework.stereotype.Component;

@Component
public class Product
{

	private String productName;
	private int productId;
	private double price;
	private String category;
	private String description;
	private double weight;

	public Product()
	{

	}

	public Product(String productName, int productId, double price, String category, String description, double weight)
	{
		super();
		this.productName = productName;
		this.productId = productId;
		this.price = price;
		this.category = category;
		this.description = description;
		this.weight = weight;
	}

	public String getProductName()
	{
		return productName;
	}

	public void setProductName(String productName)
	{
		this.productName = productName;
	}

	public int getProductId()
	{
		return productId;
	}

	public void setProductId(int productId)
	{
		this.productId = productId;
	}

	public double getPrice()
	{
		return price;
	}

	public void setPrice(double price)
	{
		this.price = price;
	}

	public String getCategory()
	{
		return category;
	}

	public void setCategory(String category)
	{
		this.category = category;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public double getWeight()
	{
		return weight;
	}

	public void setWeight(double weight)
	{
		this.weight = weight;
	}

}
