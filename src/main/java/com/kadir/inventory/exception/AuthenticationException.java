package com.kadir.inventory.exception;

public class AuthenticationException extends Exception
{
	private String message;

	public AuthenticationException()
	{
		super();
	}

	public AuthenticationException(String message)
	{
		super();
		this.message = message;
	}

	public AuthenticationException(Throwable cause)
	{
		super(cause);
	}

	@Override
	public String toString()
	{
		return message;
	}

	@Override
	public String getMessage()
	{
		return message;
	}
}
