package com.nagarro.training.userVerificationApi.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.training.userVerificationApi.customExceptions.ValidationException;
import com.nagarro.training.userVerificationApi.model.ErrorDetails;
import com.nagarro.training.userVerificationApi.model.User;
import com.nagarro.training.userVerificationApi.model.UserDataResponse;
import com.nagarro.training.userVerificationApi.service.UserDataRetrievalService;
import com.nagarro.training.userVerificationApi.service.UserVerificationService;


import reactor.core.publisher.Flux;

/**
 * Controller class for handling user verification-related endpoints.
 *
 * This class defines two main endpoints:
 * 1. POST "/user" - Retrieves a random user or multiple users based on the provided size parameter.
 * 2. GET "/users" - Retrieves user data with sorting, limit, and offset parameters.
 *
 * The class also includes exception handlers for specific exceptions, ensuring proper error responses.
 *
 * Additionally, there are helper methods for parsing and validating input parameters.
 *@author shreyarathour
 * @RestController Indicates that this class serves as a controller for Spring MVC and provides RESTful endpoints.
 */

@RestController
public class UserVerificationApiController {

    private final UserDataRetrievalService userDataRetrievalService;
    private final UserVerificationService userVerificationService;

    @Autowired
    public UserVerificationApiController(UserVerificationService userVerificationService,
                                        UserDataRetrievalService userDataRetrievalService) {
        this.userVerificationService = userVerificationService;
        this.userDataRetrievalService = userDataRetrievalService;
    }

    

    /**
     * Endpoint to get random user(s) based on the specified size.
     *
     * @param size The number of random users to retrieve (default is 1 if not specified)
     * @return Flux of User objects representing random user data
     */
    @PostMapping("/user")
    public Flux<User> getRandomUser(@RequestParam(name = "results", defaultValue = "1") String size) {
        try {
            int parsedSize = parseSize(size);// Parsing the size parameter

            validateSize(parsedSize);// Validating the parsed size

            return this.userVerificationService.verifyUser(parsedSize);// Returning verified user(s)
        } catch (NumberFormatException e) {
            throw new ValidationException("Invalid size format. Please enter an integer for size parameter");
        }
    }
    
    /**
     * Endpoint to retrieve user data with sorting, pagination, and limits.
     *
     * @param sortType   The type of sorting 
     * @param sortOrder  The order of sorting 
     * @param limit      The maximum number of users to retrieve (default is 5 if not specified)
     * @param offset     The offset for pagination (default is 0 if not specified)
     * @return ResponseEntity containing UserDataResponse with sorted and paginated user data
     */
    @GetMapping("/users")
    public ResponseEntity<UserDataResponse> retrieveUserData(@RequestParam(name = "sortType") String sortType,
                                                    @RequestParam(name = "sortOrder") String sortOrder,
                                                    @RequestParam(name = "limit", defaultValue = "5") int limit,
                                                    @RequestParam(name = "offset", defaultValue = "0") int offset) {
        UserDataResponse users = this.userDataRetrievalService.retrieveUserData(sortType, sortOrder, limit, offset);
        return ResponseEntity.ok(users);// Returning user data in a ResponseEntity
    }
    
    
    /**
     * Exception handler for ValidationException.
     *
     * @param ex ValidationException instance
     * @return ResponseEntity containing ErrorDetails for bad requests
     */

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorDetails> handleValidationException(ValidationException ex) {
    	ErrorDetails errorDetails=new ErrorDetails(HttpStatus.BAD_REQUEST.value(),ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
    }

    /**
     * Generic exception handler for handling other exceptions.
     *
     * @param ex Exception instance
     * @return ResponseEntity containing ErrorDetails for bad requests
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleException(Exception ex) {
    	ErrorDetails errorDetails=new ErrorDetails(HttpStatus.BAD_REQUEST.value(),ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
    }
    
    // Helper method to parse the size parameter
    private int parseSize(String size) {
        try {
            return Integer.parseInt(size);
        } catch (NumberFormatException e) {
            throw new ValidationException("Whoops! It looks like there's an issue with the size parameter. Please enter a numeric value :)");
        }
    }

    // Helper method to validate the size parameter
    private void validateSize(int size) {
        if (size < 1 || size > 5) {
            throw new ValidationException("Uh-oh! The result size must be an integer between 1 and 5. Check your input and try again :) ");
        }
    }
}

