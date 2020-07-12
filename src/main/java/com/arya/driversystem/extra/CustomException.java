package com.arya.driversystem.extra;

/**
 * This is the Custom Exception class for Business Exception
 * @author arya
 *
 */
public class CustomException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CustomException(String msg, Exception e) {
		super(msg, e);
	}

}
