package com.jumpee.commerce.exception;

public class TokenHandlerException  extends IllegalArgumentException
{
	private static String message;

	public TokenHandlerException()
	{
		super(message);
	}
}
