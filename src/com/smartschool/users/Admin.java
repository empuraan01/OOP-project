package com.smartschool.users;

import com.smartschool.auth.PasswordUtils;
import com.smartschool.notifications.Notifier;

/**
 * Represents an Administrator user in the Smart School Management System.
 * Admins have the highest level of access and can manage all aspects of the system.
 * 
 * <p>This class demonstrates:
 * <ul>
 *   <li>Hierarchical inheritance (extends User)</li>
 *   <li>Multiple inheritance via interfaces (implements Role, Notifier)</li>
 * </ul>
 * 
 * @author Smart School Development Team
 * @version 1.0
 */
public class Admin extends User implements Role, Notifier {
    
    private String department;
    private int accessLevel;
    
    /**
     * Constructs a new Admin with basic information.
     * 
     * @param id Unique identifier for the admin
     * @param name Full name of the admin
     * @param email Email address of the admin
     * @param password Encrypted password
     */
    public Admin(String id, String name, String email, String password) {
        super(id, name, email, password);
        this.department = "Administration";
        this.accessLevel = 3; // Highest access level
    }
    
    /**
     * Constructs a new Admin with department information.
     * 
     * @param id Unique identifier for the admin
     * @param name Full name of the admin
     * @param email Email address of the admin
     * @param password Encrypted password
     * @param department Department the admin belongs to
     */
    public Admin(String id, String name, String email, String password, String department) {
        super(id, name, email, password);
        this.department = department;
        this.accessLevel = 3;
    }
    
    /**
     * Authenticates the admin by comparing passwords.
     * 
     * @param inputPassword The password provided by the user
     * @return true if passwords match, false otherwise
     */
    @Override
    public boolean authenticate(String inputPassword) {
        return getPassword() != null && PasswordUtils.PasswordHasher.verifyPassword(inputPassword, getPassword());
    }
    
    /**
     * Returns the role of this user.
     * 
     * @return "Admin" as the role
     */
    @Override
    public String getRole() {
        return "Admin";
    }
    
    /**
     * Performs administrative duties.
     * Implementation of Role interface.
     * 
     * @return A description of administrative duties performed
     */
    @Override
    public String performRoleDuties() {
        return "Managing school operations, user accounts, and system configuration.";
    }
    
    /**
     * Returns the role name.
     * Implementation of Role interface.
     * 
     * @return "Administrator"
     */
    @Override
    public String getRoleName() {
        return "Administrator";
    }
    
    /**
     * Sends a notification to another user.
     * Implementation of Notifier interface.
     * 
     * @param recipientId The ID of the recipient
     * @param message The message to send
     * @return true if sent successfully
     */
    @Override
    public boolean sendNotification(String recipientId, String message) {
        System.out.println("Admin " + getName() + " sent notification to " + recipientId + ": " + message);
        return true;
    }
    
    /**
     * Receives a notification.
     * Implementation of Notifier interface.
     * 
     * @param message The notification message
     */
    @Override
    public void receiveNotification(String message) {
        System.out.println("Admin " + getName() + " received: " + message);
    }
    
    /**
     * Gets the department.
     * 
     * @return The department name
     */
    public String getDepartment() {
        return department;
    }
    
    /**
     * Sets the department.
     * 
     * @param department The department name
     */
    public void setDepartment(String department) {
        this.department = department;
    }
    
    /**
     * Gets the access level.
     * 
     * @return The access level (1-3, where 3 is highest)
     */
    public int getAccessLevel() {
        return accessLevel;
    }
    
    /**
     * Sets the access level.
     * 
     * @param accessLevel The access level (1-3)
     */
    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }
}

