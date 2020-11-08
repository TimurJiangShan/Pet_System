package com.example.exception;

/**
 * register error
 * @Chao Wang
 * 10/10/2020
 *
 * TODO
 */
public class RootUserException extends RuntimeException{

	public RootUserException(String message, Throwable cause) {
		super(message, cause);
	}

	public RootUserException(String message) {
		super(message);
	}

}
