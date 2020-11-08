package com.example.exception;


public class OperationFailedException extends RuntimeException{

	public OperationFailedException(String message, Throwable cause) {
		super(message, cause);
	}

	public OperationFailedException(String message) {
		super(message);
	}

}
