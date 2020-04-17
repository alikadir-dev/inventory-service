package com.kadir.inventory.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Mohammad Kadir Ali
 */
@Document(collection = "inventroy_info")
public class Inventory implements Serializable
{
	@Id
	private ObjectId id;
	private List<Product> productArray;
	private String orderBy;
	private String orderOn;
	private String paymentMode;

	private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	public Inventory()
	{
	}

	public Inventory( List<Product> productArray, String orderBy, String orderOn, String paymentMode)
	{
		super();
		this.productArray = productArray;
		this.orderBy = orderBy;
		this.orderOn = orderOn;
		this.paymentMode = paymentMode;
	}

	
	public Inventory(ObjectId id, List<Product> productArray, String orderBy, String orderOn, String paymentMode)
	{
		super();
		this.id = id;
		this.productArray = productArray;
		this.orderBy = orderBy;
		this.orderOn = orderOn;
		this.paymentMode = paymentMode;
	}

	public ObjectId getId()
	{
		return id;
	}

	public void setId(ObjectId id)
	{
		this.id = id;
	}

	public List<Product> getProductArray()
	{
		return productArray;
	}

	public void setProductArray(List<Product> productArray)
	{
		this.productArray = productArray;
	}

	public String getOrderBy()
	{
		return orderBy;
	}

	public void setOrderBy(String orderBy)
	{
		this.orderBy = orderBy;
	}

	public String getOrderOn()
	{
		return orderOn;
	}

	public void setOrderOn(String orderOn)
	{
		this.orderOn = orderOn;
	}

	public String getPaymentMode()
	{
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode)
	{
		this.paymentMode = paymentMode;
	}

}
