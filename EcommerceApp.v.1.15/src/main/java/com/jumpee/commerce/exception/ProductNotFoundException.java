package com.jumpee.commerce.exception;

public class ProductNotFoundException extends IllegalArgumentException
{
	private static String message;

	public ProductNotFoundException()
	{
		super(message);
	}
}
