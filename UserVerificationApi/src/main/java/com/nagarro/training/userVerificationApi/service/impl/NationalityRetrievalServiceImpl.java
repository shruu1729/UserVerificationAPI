package com.nagarro.training.userVerificationApi.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.JsonNode;
import com.nagarro.training.userVerificationApi.service.NationalityRetrievalService;


/**
 * Implementation of the NationalityRetrievalService interface for retrieving nationalities.
 * This service uses a WebClient to communicate with a third-party API.
 *  @author shreyarathour
 */
@Service
public class NationalityRetrievalServiceImpl implements NationalityRetrievalService {
	/**
     * Constructor for NationalityRetrievalServiceImpl.
     * @param getNationalityWebClient Autowired WebClient bean with the qualifier "NationalityApiWebClient".
     */
	private final WebClient webClient;
    public NationalityRetrievalServiceImpl(@Autowired @Qualifier("NationalityApiWebClient") WebClient getNationalityWebClient) {
        this.webClient = getNationalityWebClient;
    }


    /**
     * Retrieves a list of nationalities based on the provided name.
     * @param name The name for which to retrieve nationalities.
     * @return List of nationalities as strings.
     */
	@Override
	public List<String> getNationalities(String name){
		  // Perform a GET request to the Nationality API using WebClient
		return webClient.get()
						.uri("?name={name}", name)
						.retrieve()
						 // Extract countries from the JSON response
						.bodyToMono(JsonNode.class)
						.map(jsonNode ->{
							List<String> nationalities = new ArrayList<>();
							JsonNode countries = jsonNode.get("country");
							 // Check if countries node exists and is an array
							if(countries != null && countries.isArray()) {
								// Iterate over countries and extract country_id
								countries.elements().forEachRemaining(country ->{
									String countryId = country.get("country_id").asText();
									nationalities.add(countryId);
								});
							}
							return nationalities;
						}).block();// Block and retrieve the result (synchronous operation in this case)
	}

}
