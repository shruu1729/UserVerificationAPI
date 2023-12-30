package com.nagarro.training.userVerificationApi.service;
import com.nagarro.training.userVerificationApi.model.UserDataResponse;
/**
 * @author shreyarathour
 * Interface for a service that retrieves user data with specified sorting, limit, and offset.
 */
public interface UserDataRetrievalService {
	/**
     * Retrieves user data with sorting, limit, and offset.
     *
     * @param sortType   Type of sorting (e.g., name, age)
     * @param sortOrder  Order of sorting (e.g., even, odd)
     * @param limit      Maximum number of results to retrieve
     * @param offset     Offset for pagination
     * @return UserDataResponse containing user data and pagination info
     */
	UserDataResponse retrieveUserData(String sortType, String sortOrder, int limit, int offset);
	//UserDataResponse retrieveUserData(String sortType, String sortOrder,int limit,int offset);

}
