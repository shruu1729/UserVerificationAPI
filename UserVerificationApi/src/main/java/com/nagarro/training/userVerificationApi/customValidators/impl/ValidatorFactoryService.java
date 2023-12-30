package com.nagarro.training.userVerificationApi.customValidators.impl;

import com.nagarro.training.userVerificationApi.customValidators.CustomValidator;

/**
 * @author shreyarathour
 * A factory service for providing custom validators based on the input type.
 * Supports validators for Integer and String types.
 */
public class ValidatorFactoryService {
	
	private static final CustomValidator<Integer> numericValidator = new CustomNumericValidatorImpl();
    private static final CustomValidator<String> alphabetsValidator = new CustomAlphabetsValidatorImpl();
    /**
     * Factory method to get the appropriate validator based on the input type.
     *
     * @param input Object to determine the type of validator needed
     * @return CustomValidator for the specified input type
     * @throws IllegalArgumentException if the input type is not supported
     */
    // Factory method to get the appropriate validator based on the input type
    public static CustomValidator<?> getValidator(Object input) {
        if (input instanceof Integer) {
        	
                return numericValidator;// Returns numeric validator if the input is an Integer
                
            } else if (input instanceof String) {
            	
                return alphabetsValidator;// Returns alphabets validator if the input is a String
                
            }else {
            	
            throw new IllegalArgumentException("Unsupported input type");// Throws exception for unsupported input types
        }
    }
}
