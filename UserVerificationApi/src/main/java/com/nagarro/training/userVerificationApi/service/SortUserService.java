package com.nagarro.training.userVerificationApi.service;

import java.util.List;

import com.nagarro.training.userVerificationApi.model.User;
/**
 * @author shreyarathour
 * Interface defining methods for sorting a list of users based on different criteria.
 */
public interface SortUserService {
	/**
     * Sorts the list of users based on their names and filters odd indices.
     *
     * @param userList List of User objects to be sorted
     * @return List of sorted User objects with names at odd indices
     */
	List<User> basedOnNameAndOdd(List<User> userList);
	/**
     * Sorts the list of users based on their names and filters even indices.
     *
     * @param userList List of User objects to be sorted
     * @return List of sorted User objects with names at even indices
     */
	List<User> basedOnNameAndEven(List<User> userList);
	/**
     * Sorts the list of users based on their ages and filters odd indices.
     *
     * @param userList List of User objects to be sorted
     * @return List of sorted User objects with ages at odd indices
     */
	List<User> basedOnAgeAndOdd(List<User> userList);
	/**
     * Sorts the list of users based on their ages and filters even indices.
     *
     * @param userList List of User objects to be sorted
     * @return List of sorted User objects with ages at even indices
     */
	List<User> basedOnAgeAndEven(List<User> userList);

}
