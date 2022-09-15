package com.jumpee.commerce.exception;

public class AuthCheckerHandler extends IllegalArgumentException
{
	private static String message;
	
	public AuthCheckerHandler()
	{
		super(message);
	}
}
