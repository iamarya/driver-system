package com.arya.driversystem.extra;

public class CustomException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CustomException(String msg, Exception e) {
		super(msg, e);
	}

}
