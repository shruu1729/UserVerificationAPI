package com.nagarro.training.userVerificationApi.customExceptions;
/**
 * Custom exception class for validation errors.
 * @author shreyarathour
 */

public class ValidationException extends RuntimeException {
	/**
     * Constructor for ValidationException with a custom error message.
     *
     * @param message Error message describing the validation issue.
     */
	
	private static final long serialVersionUID = 1L;

	public ValidationException(String message) {
		super(message);
	}

}
