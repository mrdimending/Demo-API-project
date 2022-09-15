package com.jumpee.commerce.exception;

public class EmailCheckException extends IllegalArgumentException
{
	private static String message;

	public EmailCheckException()
	{
		super(message);
	}
}