package com.jumpee.commerce.exception;

public class NotFoundHandler extends IllegalArgumentException
{
	private static String message;

	public NotFoundHandler()
	{
		super(message);
	}
}
