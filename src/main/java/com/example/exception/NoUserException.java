package com.example.exception;

public class NoUserException extends RuntimeException{

	public NoUserException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoUserException(String message) {
		super(message);
	}

}
