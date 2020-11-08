package com.example.exception;

/**
 * register error
 * @Chao Wang
 * 10/10/202
 *
 * TODO
 */
public class NoUserException extends RuntimeException{

	public NoUserException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoUserException(String message) {
		super(message);
	}

}
