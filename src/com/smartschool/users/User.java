package com.smartschool.users;

/**
 * Abstract base class representing a user in the Smart School Management System.
 * This class provides common functionality and attributes for all user types
 * (Admin, Teacher, Student) and defines abstract methods that must be implemented
 * by subclasses.
 * 
 * <p>This class demonstrates:
 * <ul>
 *   <li>Abstract class design pattern</li>
 *   <li>Encapsulation with private fields and public getters/setters</li>
 *   <li>Constructor overloading</li>
 * </ul>
 * 
 * @author Smart School Development Team
 * @version 1.0
 */
public abstract class User {
    
    // Private fields demonstrating encapsulation
    private String id;
    private String name;
    private String email;
    private String password;
    
    /**
     * Constructor with basic user information.
     * Password is set to null and must be set separately.
     * 
     * @param id Unique identifier for the user
     * @param name Full name of the user
     * @param email Email address of the user
     */
    public User(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = null;
    }
    
    /**
     * Constructor with all user information including password.
     * This demonstrates constructor overloading.
     * 
     * @param id Unique identifier for the user
     * @param name Full name of the user
     * @param email Email address of the user
     * @param password Encrypted password for the user
     */
    public User(String id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }
    
    /**
     * Abstract method that must be implemented by subclasses.
     * Each user type has different authentication requirements.
     * 
     * @param inputPassword The password provided by the user
     * @return true if authentication is successful, false otherwise
     */
    public abstract boolean authenticate(String inputPassword);
    
    /**
     * Abstract method that returns the role of the user.
     * 
     * @return A string representing the user's role (e.g., "Admin", "Teacher", "Student")
     */
    public abstract String getRole();
    
    /**
     * Gets the user's unique identifier.
     * 
     * @return The user ID
     */
    public String getId() {
        return id;
    }
    
    /**
     * Sets the user's unique identifier.
     * 
     * @param id The user ID to set
     */
    public void setId(String id) {
        this.id = id;
    }
    
    /**
     * Gets the user's full name.
     * 
     * @return The user's name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Sets the user's full name.
     * 
     * @param name The name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Gets the user's email address.
     * 
     * @return The user's email
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * Sets the user's email address.
     * 
     * @param email The email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * Gets the user's encrypted password.
     * 
     * @return The encrypted password
     */
    protected String getPassword() {
        return password;
    }
    
    /**
     * Sets the user's password (should be encrypted).
     * 
     * @param password The encrypted password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * Returns a string representation of the user.
     * 
     * @return A formatted string with user information
     */
    @Override
    public String toString() {
        return String.format("User{id='%s', name='%s', email='%s', role='%s'}", 
                           id, name, email, getRole());
    }
}

