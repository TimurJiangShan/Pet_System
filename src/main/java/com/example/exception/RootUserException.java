package com.example.exception;


public class RootUserException extends RuntimeException{

	public RootUserException(String message, Throwable cause) {
		super(message, cause);
	}

	public RootUserException(String message) {
		super(message);
	}

}
