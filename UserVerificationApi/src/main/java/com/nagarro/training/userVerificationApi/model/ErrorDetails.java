package com.nagarro.training.userVerificationApi.model;

import java.time.LocalDateTime;

/**
 * @author shreyarathour
 * Represents details of an error, including error code, error message, and timestamp.
 */
public class ErrorDetails {

    private int errorCode;// Error code associated with the error
    private String errorMessage;// Error message describing the nature of the error
    private LocalDateTime errorTimestamp;// Timestamp when the error occurred

    /**
     * Constructor to initialize ErrorDetails with error code, error message, and current timestamp.
     *
     * @param errorCode    The error code to be set
     * @param errorMessage The error message to be set
     */
    public ErrorDetails(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.errorTimestamp = LocalDateTime.now();
    }
    /**
     * Getter method to retrieve the error code.
     *
     * @return The error code
     */
    public int getErrorCode() {
        return errorCode;
    }
    /**
     * Setter method to set the error code.
     *
     * @param errorCode The error code to be set
     */
    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
    /**
     * Getter method to retrieve the error message.
     *
     * @return The error message
     */
    public String getErrorMessage() {
        return errorMessage;
    }
    /**
     * Setter method to set the error message.
     *
     * @param errorMessage The error message to be set
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    /**
     * Getter method to retrieve the timestamp when the error occurred.
     *
     * @return The timestamp of the error
     */
    public LocalDateTime getErrorTimestamp() {
        return errorTimestamp;
    }
    /**
     * Setter method to set the timestamp of the error.
     *
     * @param errorTimestamp The timestamp to be set
     */
    public void setErrorTimestamp(LocalDateTime errorTimestamp) {
        this.errorTimestamp = errorTimestamp;
    }
}
