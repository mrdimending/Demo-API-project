package com.jumpee.commerce.exception;

public class AuthHandlerException extends IllegalArgumentException
{
	private static String message;

	public AuthHandlerException()
	{
		super(message);
	}
}
