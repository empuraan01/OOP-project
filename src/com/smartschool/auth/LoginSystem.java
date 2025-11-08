package com.smartschool.auth;

import com.smartschool.exceptions.InvalidLoginException;
import com.smartschool.users.User;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Handles user authentication and login operations.
 * This class demonstrates exception handling and Scanner usage for I/O operations.
 * 
 * <p>This class demonstrates:
 * <ul>
 *   <li>Exception handling (InvalidLoginException)</li>
 *   <li>Scanner for user input (I/O operations)</li>
 * </ul>
 * 
 * @author Smart School Development Team
 * @version 1.0
 */
public class LoginSystem {
    
    private Map<String, User> users;
    private User currentUser;
    
    /**
     * Constructs a new LoginSystem.
     */
    public LoginSystem() {
        this.users = new HashMap<>();
        this.currentUser = null;
    }
    
    /**
     * Registers a new user in the system.
     * 
     * @param user The user to register
     */
    public void registerUser(User user) {
        users.put(user.getId(), user);
    }
    
    /**
     * Attempts to login a user with the provided credentials.
     * 
     * @param userId The user ID
     * @param password The password
     * @return The authenticated User object
     * @throws InvalidLoginException if login credentials are invalid
     */
    public User login(String userId, String password) throws InvalidLoginException {
        User user = users.get(userId);
        
        if (user == null) {
            throw new InvalidLoginException("User ID not found: " + userId);
        }
        
        if (!user.authenticate(password)) {
            throw new InvalidLoginException("Invalid password for user: " + userId);
        }
        
        currentUser = user;
        return user;
    }
    
    /**
     * Interactive login method using Scanner for user input.
     * This demonstrates I/O operations with Scanner.
     * 
     * @param scanner The Scanner object for reading input
     * @return The authenticated User object, or null if login was cancelled
     */
    public User interactiveLogin(Scanner scanner) {
        try {
            System.out.print("Enter User ID: ");
            String userId = scanner.nextLine().trim();
            
            System.out.print("Enter Password: ");
            String password = scanner.nextLine().trim();
            
            return login(userId, password);
        } catch (InvalidLoginException e) {
            System.out.println("Login failed: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Logs out the current user.
     */
    public void logout() {
        currentUser = null;
    }
    
    /**
     * Gets the currently logged-in user.
     * 
     * @return The current user, or null if no user is logged in
     */
    public User getCurrentUser() {
        return currentUser;
    }
    
    /**
     * Checks if a user is currently logged in.
     * 
     * @return true if a user is logged in, false otherwise
     */
    public boolean isLoggedIn() {
        return currentUser != null;
    }
    
    /**
     * Gets all registered users.
     * 
     * @return Map of all users
     */
    public Map<String, User> getUsers() {
        return new HashMap<>(users);
    }
}

