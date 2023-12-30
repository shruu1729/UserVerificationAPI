package com.nagarro.training.userVerificationApi.model;

import java.util.List;
/**
 * @author shreyarathour
 * Represents the response containing user data and pagination information.
 * This class is used to encapsulate a subset of user data along with details for pagination.
 */
public class UserDataResponse {
	
	private List<User> userData;// List of user data
	private PaginationInfo paginationInfo;// Pagination information
	
	public UserDataResponse() {
		
	}
	
	
	/**
     * Parameterized constructor to set user data and pagination information.
     *
     * @param usersSubset List of user data
     * @param paginationInfo Pagination information
     */
	public UserDataResponse(List<User> usersSubset, PaginationInfo paginationInfo) {
		super();
		this.userData = usersSubset;
		this.paginationInfo = paginationInfo;
	}
	/**
     * Getter for user data.
     *
     * @return List of user data
     */
	public List<User> getUserData() {
		return userData;
	}
	/**
     * Setter for user data.
     *
     * @param userData List of user data
     */
	public void setUserData(List<User> userData) {
		this.userData = userData;
	}
	/**
     * Getter for pagination information.
     *
     * @return PaginationInfo object
     */
	public PaginationInfo getPageInfo() {
		return paginationInfo;
	}
	/**
     * Setter for pagination information.
     *
     * @param paginationInfo PaginationInfo object
     */
	public void setPageInfo(PaginationInfo paginationInfo) {
		this.paginationInfo = paginationInfo;
	}
	/**
     * Overridden toString method to represent the object as a string.
     *
     * @return String representation of the object
     */
	@Override
	public String toString() {
		return "Response [userData=" + userData + ", pageInfo=" + paginationInfo + "]";
	}
	
	
}
