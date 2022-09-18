package com.jumpee.commerce.exception;

public class TransactionNotFoundException extends IllegalArgumentException
{
	private static String message;

	public TransactionNotFoundException()
	{
		super(message);
	}
}

