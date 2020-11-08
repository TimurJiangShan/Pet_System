package com.example.exception;

public class RepeatUserException extends RuntimeException{

	public RepeatUserException(String message, Throwable cause) {
		super(message, cause);
	}

	public RepeatUserException(String message) {
		super(message);
	}

}
