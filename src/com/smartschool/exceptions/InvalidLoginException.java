package com.smartschool.exceptions;

/**
 * Custom exception thrown when login credentials are invalid.
 * This exception is used in the authentication system to indicate
 * that a user's login attempt has failed due to incorrect credentials.
 * 
 * @author Smart School Development Team
 * @version 1.0
 */
public class InvalidLoginException extends Exception {
    
    /**
     * Constructs a new InvalidLoginException with a default message.
     */
    public InvalidLoginException() {
        super("Invalid login credentials. Please check your username and password.");
    }
    
    /**
     * Constructs a new InvalidLoginException with a custom message.
     * 
     * @param message The detail message explaining the login failure
     */
    public InvalidLoginException(String message) {
        super(message);
    }
    
    /**
     * Constructs a new InvalidLoginException with a custom message and cause.
     * 
     * @param message The detail message
     * @param cause The cause of this exception
     */
    public InvalidLoginException(String message, Throwable cause) {
        super(message, cause);
    }
}

