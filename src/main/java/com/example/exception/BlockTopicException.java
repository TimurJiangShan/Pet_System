package com.example.exception;


public class BlockTopicException extends RuntimeException{

	public BlockTopicException(String message, Throwable cause) {
		super(message, cause);
	}

	public BlockTopicException(String message) {
		super(message);
	}

}
