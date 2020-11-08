package com.example.exception;

public class OperationRepeaException extends RuntimeException{

	public OperationRepeaException(String message, Throwable cause) {
		super(message, cause);
	}

	public OperationRepeaException(String message) {
		super(message);
	}

}
