package com.example.exception;


public class NoTopicException extends RuntimeException{

	public NoTopicException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoTopicException(String message) {
		super(message);
	}

}
