package com.nagarro.training.userVerificationApi.service;

//Interface defining a service for gender retrieval based on a name
public interface GenderRetrievalService {
	/**
     * Retrieves the gender associated with the given name.
     *
     * @param name The name for which gender needs to be retrieved
     * @return Gender associated with the provided name
     */

	String getGender(String name);

}
