package com.melodyguard.api.exception;

public class SearchTypeException extends RuntimeException {
	private String message;

	public SearchTypeException() {
		super();
	}

	public SearchTypeException(String msg) {
		super(msg);
		this.message = msg;
	}
}
