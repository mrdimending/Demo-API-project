package com.jumpee.commerce.exception;

public class NullHandlerException extends NullPointerException
{
	private static String message;
	
	public NullHandlerException()
	{
		super(message);
	}
}