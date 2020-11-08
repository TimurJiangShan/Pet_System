package com.example.exception;


public class OperationSystemException extends RuntimeException{

	public OperationSystemException(String message, Throwable cause) {
		super(message, cause);
	}

	public OperationSystemException(String message) {
		super(message);
	}

}
