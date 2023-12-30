package com.nagarro.training.userVerificationApi.service.impl;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.JsonNode;
import com.nagarro.training.userVerificationApi.model.User;
import com.nagarro.training.userVerificationApi.repository.UserRepository;
import com.nagarro.training.userVerificationApi.service.GenderRetrievalService;
import com.nagarro.training.userVerificationApi.service.NationalityRetrievalService;
import com.nagarro.training.userVerificationApi.service.UserVerificationService;

import reactor.core.publisher.Flux;
/**
 * Service implementation responsible for verifying user data obtained from an external API.
 * It interacts with different services to retrieve user-related information and performs verification.
 * Uses WebClient to fetch data from an external source and manipulates the data to verify users.
 *  Features:
 * - Verification of users based on gender and nationality.
 * - Asynchronous retrieval of gender and nationality information.
 * - Utilizes a fixed-size thread pool for parallel processing.
 *
 * Note: This class uses CompletableFuture for asynchronous operations.
 *
 * @author shreyarathour
 */
@Service
public class UserVerificationServiceImpl implements UserVerificationService {
	
	 
	 private final NationalityRetrievalService nationalityService;
	 private final GenderRetrievalService genderService;
	 private final WebClient webClient;
	 
	 @Autowired
	 private UserRepository userRepository;
	 // Creating an executor service with two threads
	 ExecutorService executorService = Executors.newFixedThreadPool(2);
	 /**
	     * Constructor for UserVerificationServiceImpl.
	     * 
	     * @param nationalityService The service to retrieve nationalities.
	     * @param genderService      The service to retrieve gender.
	     * @param getRandomUserWebClient The WebClient bean for API 1.
	     */
	    public UserVerificationServiceImpl(NationalityRetrievalService nationalityService,
	            GenderRetrievalService genderService, @Autowired @Qualifier("randomUserApiWebClient") WebClient getRandomUserWebClient) {
	        this.nationalityService = nationalityService;
	        this.genderService = genderService;
	        this.webClient = getRandomUserWebClient; // Inject the WebClient bean for API 1
	    }
	    /**
	     * Marks the verification status of a user based on gender and nationality.
	     * 
	     * @param user         The user to be verified.
	     * @param gender       The gender to check against.
	     * @param nationalities The list of nationalities to check against.
	     * @return true if the user is verified, false otherwise.
	     */
	 private Boolean markVerificationStatus(User user, String gender, List<String> nationalities) {
		
		 if(nationalities.contains(user.getNationality()) && user.getGender().equals(gender)){
			 return true; 
		 }
		return false;
		 
	 }

	 /**
	     * Retrieves and verifies users from a third-party API.
	     * 
	     * @param size The number of users to retrieve.
	     * @return Flux of User objects representing verified user data.
	     */
	 
	@Override
	public Flux<User> verifyUser(int size) {
		// TODO Auto-generated method stub
		
		return webClient.get()
				.uri("?results={size}", size)
				.retrieve()
				.bodyToMono(JsonNode.class)
				.flatMapMany(results -> Flux.fromIterable(results.path("results")))
				.flatMap(result ->{
					System.out.println("API1 is running" + size);
					String gender = result.get("gender").asText();
					String nationality = result.get("nat").asText();
					String firstName = result.path("name").get("first").asText();
					String lastName = result.path("name").get("last").asText();
					int age = Integer.parseInt(result.path("dob").get("age").asText());
					String dob = result.path("dob").get("date").asText();
					String name = firstName+" "+lastName;
					System.out.println(name);
					User user = new User(gender, nationality, name,age, dob);
					
					CompletableFuture<String> genderFuture = CompletableFuture.supplyAsync(() -> 
																	this.genderService.getGender(firstName), executorService);
					
					CompletableFuture<List<String>> nationalitiesFuture = CompletableFuture.supplyAsync(() -> 
																		this.nationalityService.getNationalities(firstName), executorService);
					
					CompletableFuture.allOf(genderFuture, nationalitiesFuture).join();
					
					try {
						String genderApi = genderFuture.get();
						List<String> nationalitiesApi = nationalitiesFuture.get();
						System.out.println(genderApi+" "+ nationalitiesApi );
						if(markVerificationStatus(user, genderApi, nationalitiesApi)) {
							user.setVerificationStatus("VERIFIED");
						}else {
							user.setVerificationStatus("TO_BE_VERIFIED");
						}
						this.userRepository.save(user);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ExecutionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					return Flux.just(user);
				});
	}
}
