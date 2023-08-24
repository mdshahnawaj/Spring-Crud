package com.customer.exception;

/**
 * This class is used to print response message on postman when id is not found in database
 */
public class ResourceNotFoundException extends RuntimeException {

	public ResourceNotFoundException(String message) {
		super(message);
	}

}