package com.shopnow.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotfoundException extends RuntimeException 
{

	private static final long serialVersionUID = 1L;

	public UserNotfoundException(String message) 
	{
		super(message);
	}

}