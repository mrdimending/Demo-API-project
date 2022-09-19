package com.jumpee.commerce.exception;

public class InsufficientBalanceException extends IllegalArgumentException
{
	private static String message;

	public InsufficientBalanceException()
	{
		super(message);
	}
}
