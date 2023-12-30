package com.nagarro.training.userVerificationApi.service;

import com.nagarro.training.userVerificationApi.model.User;

import reactor.core.publisher.Flux;
/**
 * @author shreyarathour
 * Service interface for retrieving user data with sorting, limiting, and offset.
 */
public interface UserVerificationService {

    /**
     * Verifies and retrieves a specified number of users.
     *
     * @param size Number of users to verify and retrieve
     * @return Flux of User objects representing verified users
     */
	Flux<User> verifyUser(int size);

}
