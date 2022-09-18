package com.jumpee.commerce.exception;

public class StockHandlerException extends IllegalArgumentException
{
	private static String message;

	public StockHandlerException()
	{
		super(message);
	}
}
