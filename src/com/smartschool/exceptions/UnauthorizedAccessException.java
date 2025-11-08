package com.smartschool.exceptions;

/**
 * Custom exception thrown when a user attempts to access resources
 * or perform actions they are not authorized to access.
 * 
 * @author Smart School Development Team
 * @version 1.0
 */
public class UnauthorizedAccessException extends Exception {
    
    /**
     * Constructs a new UnauthorizedAccessException with a default message.
     */
    public UnauthorizedAccessException() {
        super("Unauthorized access. You do not have permission to perform this action.");
    }
    
    /**
     * Constructs a new UnauthorizedAccessException with a custom message.
     * 
     * @param message The detail message explaining the unauthorized access
     */
    public UnauthorizedAccessException(String message) {
        super(message);
    }
    
    /**
     * Constructs a new UnauthorizedAccessException with a custom message and cause.
     * 
     * @param message The detail message
     * @param cause The cause of this exception
     */
    public UnauthorizedAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}

