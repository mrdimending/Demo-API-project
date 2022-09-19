package com.jumpee.commerce.exception;

public class ShippingNotFound extends IllegalArgumentException
{
	private static String message;

	public ShippingNotFound()
	{
		super(message);
	}
}
