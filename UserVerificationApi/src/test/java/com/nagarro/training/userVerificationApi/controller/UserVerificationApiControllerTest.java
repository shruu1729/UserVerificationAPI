package com.nagarro.training.userVerificationApi.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;


import com.nagarro.training.userVerificationApi.UserVerificationApiApplication;
import com.nagarro.training.userVerificationApi.customExceptions.ValidationException;
import com.nagarro.training.userVerificationApi.model.User;
import com.nagarro.training.userVerificationApi.service.UserDataRetrievalService;
import com.nagarro.training.userVerificationApi.service.UserVerificationService;

import reactor.core.publisher.Flux;
/**
 * This class contains JUnit test cases for the {@link UserVerificationApiController} class.
 * It uses Mockito to mock dependencies and verify the behavior of the ApiController.
 * The tests cover various scenarios related to user verification, input validation, and exception handling.
 * The {@link MockitoJUnitRunner} is used to initialize the mocks, and the tests are executed in a Spring Boot test environment.
 * The test methods follow a standard naming convention starting with "test" to indicate that they are test cases.
 *
 * @author shreyarathour
 * @see UserVerificationApiController
 * @see ValidationException
 */
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = UserVerificationApiApplication.class)
public class UserVerificationApiControllerTest {
 
	@Mock
    private UserVerificationService userVerificationService;
	
	@Mock
	private UserDataRetrievalService userDataRetrievalService;
	
    @InjectMocks
    private UserVerificationApiController userVerificationapiController;
    
    
    /**
     * Tests the POST request of the ApiController that returns a List<User>
     * as a response entity with a OK as response status
     * 
     * @param validSize The valid size parameter for the request
     */
    @Test
    public void testVerifyUser_ValidSize_ReturnsUsersFlux() {
    	int validSize = 4;
    	 // Mocking the userVerificationService.verifyUser method to return a Flux with a User
        Mockito.when(userVerificationService.verifyUser(validSize)).thenReturn(Flux.just(new User()));
        // Performing the POST request
        Flux<User> response = userVerificationapiController.getRandomUser(String.valueOf(validSize));
 
        // Asserting that the response is not null
        assertNotNull(response);
    }
    /**
     * Tests the case where an invalid size parameter is provided, expecting a ValidationException.
     * 
     * @param invalidSize The invalid size parameter for the request
     */
    @Test
    public void testVerifyUser_InvalidSize_ThrowsValidationException() {
        String invalidSize = "invalidSize";

        try {
        	// Performing the POST request with an invalid size
            userVerificationapiController.getRandomUser(invalidSize);
        } catch (ValidationException e) {
        	 // Asserting that the expected exception message is received
            assertEquals("Whoops! It looks like there's an issue with the size parameter. Please enter a numeric value :)", e.getMessage());
        }
    }
    /**
     * Tests the case where a negative size parameter is provided, expecting a ValidationException.
     * 
     * @param negativeSize The negative size parameter for the request
     */
    @Test
    public void testVerifyUser_NegativeSize_ThrowsValidationException() {
        String negativeSize = "-2";

        try {
            userVerificationapiController.getRandomUser(negativeSize);
        } catch (ValidationException e) {
            assertEquals("Uh-oh! The result size must be an integer between 1 and 5. Check your input and try again :) ", e.getMessage());
        }
    }
    /**
     * Tests the case where a size greater than the allowed limit is provided, expecting a ValidationException.
     * 
     * @param sizeGreaterThanLimit The size parameter greater than the allowed limit for the request
     */
    @Test
    public void testVerifyUser_SizeGreaterThanLimit_ThrowsValidationException() {
        String sizeGreaterThanLimit = "10";

        try {
            userVerificationapiController.getRandomUser(sizeGreaterThanLimit);
        } catch (ValidationException e) {
            assertEquals("Uh-oh! The result size must be an integer between 1 and 5. Check your input and try again :) ", e.getMessage());
        }
    }

   
        
    }
