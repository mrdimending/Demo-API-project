package com.jumpee.commerce.model;

public class AddToCart 
{
	private int product_id;
	private int quantity;
	
	public AddToCart() {}
	public AddToCart(int product_id, int quantity) 
	{
		super();
		this.product_id = product_id;
		this.quantity = quantity;
	}

	public int getProduct_id()
	{
		return product_id;
	}
	public void setProduct_id(int product_id) 
	{
		this.product_id = product_id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
