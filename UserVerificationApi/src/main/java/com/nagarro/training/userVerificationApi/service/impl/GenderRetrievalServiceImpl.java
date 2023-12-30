package com.nagarro.training.userVerificationApi.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.JsonNode;
import com.nagarro.training.userVerificationApi.service.GenderRetrievalService;


/**
 * Implementation of GenderRetrievalService that retrieves gender information using an external API.
 * This implementation uses the WebClient to make HTTP requests to the gender API.
 *
 * @author shreyarathour
 */
@Service
public class GenderRetrievalServiceImpl implements GenderRetrievalService{
	
	
	private final WebClient webClient;
	/**
     * Constructor for GenderRetrievalServiceImpl.
     *
     * @param getGenderWebClient WebClient bean for the gender API, injected via constructor.
     */
    public GenderRetrievalServiceImpl(@Autowired @Qualifier("GenderApiWebClient") WebClient getGenderWebClient) {
        this.webClient = getGenderWebClient;
    }
	
    /**
     * Retrieves the gender of a given name from the gender API.
     *
     * @param name The name for which gender information is to be retrieved.
     * @return Gender information (Male/Female/Unknown) based on the given name.
     */
	
	@Override
	public String getGender(String name){
		 // Make a GET request to the gender API and retrieve the gender information
		return webClient.get()
						.uri("?name={name}", name)
						.retrieve()
						.bodyToMono(JsonNode.class)
						.map(jsonNode -> jsonNode.get("gender").asText())
						.block();// Block and wait for the Mono to complete, returning the gender information.
		
	}
		
	

}
