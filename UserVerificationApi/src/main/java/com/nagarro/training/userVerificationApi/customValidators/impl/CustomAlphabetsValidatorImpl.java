package com.nagarro.training.userVerificationApi.customValidators.impl;
import com.nagarro.training.userVerificationApi.customValidators.CustomValidator;


/**
 * @author shreyarathour
 * Implementation of the CustomValidator interface for validating strings containing only English alphabets.
 */
public class CustomAlphabetsValidatorImpl implements CustomValidator<String> {

	 /**
     * Validates that the input string contains only English alphabets.
     *
     * @param input The input string to be validated.
     * @throws IllegalArgumentException If the input is null or contains non-alphabetic characters.
     */
    @Override
    public void validate(String input) {
        validateNotNull(input);// Validates that the input is not null
        if (!input.matches("[a-zA-Z]+")) {
            throw new IllegalArgumentException("Input must contain only English alphabets");
        }
    }
    /**
     * Validates that the input string is not null.
     *
     * @param input The input string to be validated.
     * @throws IllegalArgumentException If the input is null.
     */
    private void validateNotNull(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input must not be null");
        }
    }
}
