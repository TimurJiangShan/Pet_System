package com.example.exception;

/**
 * repeat register/system error
 * @Chao Wang
 * 10/10/2020
 *
 * TODO
 */
public class RepeatUserException extends RuntimeException{

	public RepeatUserException(String message, Throwable cause) {
		super(message, cause);
	}

	public RepeatUserException(String message) {
		super(message);
	}

}
