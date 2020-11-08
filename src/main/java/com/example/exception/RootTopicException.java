package com.example.exception;


public class RootTopicException extends RuntimeException{

	public RootTopicException(String message, Throwable cause) {
		super(message, cause);
	}

	public RootTopicException(String message) {
		super(message);
	}

}
