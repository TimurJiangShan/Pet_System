package com.example.dto;

/**
 * json
 * @author Jiangshan
 * @param <T>
 * TBD
 */
public class Result<T> {

	private boolean success;
	private T data;
	private String error;
	public Result() {
		
	}

	//Constructor on successful
	public Result(boolean success, T data) {
		this.success = success;
		this.data = data;
	}

	//Constructor on failure
	public Result(boolean success, String error) {
		this.success = success;
		this.error = error;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "Result [success=" + success + ", data=" + data + ", error=" + error + "]";
	}
	
}
