package com.customer.exception;

/**
 * This class is used to print validation error message in postman response body
 */
public class EmailOrMobileAlreadyExists extends RuntimeException {

	public EmailOrMobileAlreadyExists(String message) {
		super(message);
	}

}