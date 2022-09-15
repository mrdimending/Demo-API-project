package com.jumpee.commerce.exception;

public class AccessDeniedException extends RuntimeException{
    
	private static String message;

	public AccessDeniedException()
	{
		super(message);
	}
}
