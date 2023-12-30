package com.nagarro.training.userVerificationApi.customValidators;

/**
 * Interface for custom validators.
 *@author shreyarathour
 * @param <T> The type of object to be validated.
 */
public interface CustomValidator<T> {
	/**
     * Validates the input object.
     *
     * @param input The object to be validated.
     */
    void validate(T input);
}
