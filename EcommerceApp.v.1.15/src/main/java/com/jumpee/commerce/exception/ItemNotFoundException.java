package com.jumpee.commerce.exception;

public class ItemNotFoundException  extends NullPointerException
{
	private static String message;
	
	public ItemNotFoundException()
	{
		super(message);
	}
}
