package com.nagarro.training.userVerificationApi.service;

import java.util.List;

/**
 * @author shreyarathour
 * Interface for a service that retrieves nationalities based on a given name.
 */
public interface NationalityRetrievalService {
	/**
     * Retrieves a list of nationalities associated with a given name.
     *
     * @param name The name for which nationalities are to be retrieved.
     * @return List of nationalities associated with the given name.
     */
	List<String> getNationalities(String name);

}
