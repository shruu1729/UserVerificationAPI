package com.nagarro.training.userVerificationApi.customValidators.impl;

import com.nagarro.training.userVerificationApi.customValidators.CustomValidator;

/**
 * Implementation of a CustomValidator for numeric values.
 *@author shreyarathour
 * @param <Integer> Type of the numeric input to be validated.
 */
public class CustomNumericValidatorImpl implements CustomValidator<Integer> {
	/**
     * Validates the given numeric input.
     *
     * @param input Numeric value to be validated.
     */
    @Override
    public void validate(Integer input) {
        validateNotNull(input);// Validates if the input is not null
        if (input < 0) {
        	// Checks if the input value is less than 0
            throw new IllegalArgumentException("Numeric value must be greater than 0");
        }
    }
    /**
     * Validates that the input is not null.
     *
     * @param input Numeric value to be checked for null.
     */
    private void validateNotNull(Integer input) {
        if (input == null) {// Checks if the input is null
            throw new IllegalArgumentException("Input must not be null");
        }
    }
}
