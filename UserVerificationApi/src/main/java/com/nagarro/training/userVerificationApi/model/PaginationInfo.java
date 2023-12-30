package com.nagarro.training.userVerificationApi.model;
/**
 * @author shreyarathour
 * Represents pagination information such as whether there is a next page,
 * whether there is a previous page, and the total number of items.
 */
public class PaginationInfo {
	
	 private boolean hasNext;    // Indicates if there is a next page
	 private boolean hasPrevious; // Indicates if there is a previous page
	 private int total;           // Total number of items

	 /**
	     * Default constructor.
	     */
	public PaginationInfo() {
	}


	/**
     * Parameterized constructor to initialize pagination information.
     *
     * @param hasNext      Indicates if there is a next page
     * @param hasPrevious  Indicates if there is a previous page
     * @param total        Total number of items
     */
	public PaginationInfo(boolean hasNext, boolean hasPrevious, int total) {
		this.hasNext = hasNext;
		this.hasPrevious = hasPrevious;
		this.total = total;
	}

	 /**
     * Gets whether there is a next page.
     *
     * @return true if there is a next page, false otherwise
     */
	public boolean isHasNext() {
		return hasNext;
	}

    /**
     * Sets whether there is a next page.
     *
     * @param hasNext true if there is a next page, false otherwise
     */

	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}
	/**
     * Gets whether there is a previous page.
     *
     * @return true if there is a previous page, false otherwise
     */

	public boolean isHasPrevious() {
		return hasPrevious;
	}

	 /**
     * Sets whether there is a previous page.
     *
     * @param hasPrevious true if there is a previous page, false otherwise
     */
	public void setHasPrevious(boolean hasPrevious) {
		this.hasPrevious = hasPrevious;
	}

	/**
     * Gets the total number of items.
     *
     * @return total number of items
     */
	public int getTotal() {
		return total;
	}

	/**
     * Sets the total number of items.
     *
     * @param total total number of items
     */
	public void setTotal(int total) {
		this.total = total;
	}


}
