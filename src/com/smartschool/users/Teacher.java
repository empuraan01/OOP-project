package com.smartschool.users;

import com.smartschool.auth.PasswordUtils;
import com.smartschool.notifications.Notifier;

/**
 * Represents a Teacher user in the Smart School Management System.
 * Teachers can manage classes, record attendance, and assign grades.
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
public class Teacher extends User implements Role, Notifier {
    
    private String subject;
    private String department;
    private int yearsOfExperience;
    
    /**
     * Constructs a new Teacher with basic information.
     * 
     * @param id Unique identifier for the teacher
     * @param name Full name of the teacher
     * @param email Email address of the teacher
     * @param password Encrypted password
     * @param subject Subject taught by the teacher
     */
    public Teacher(String id, String name, String email, String password, String subject) {
        super(id, name, email, password);
        this.subject = subject;
        this.department = "Education";
        this.yearsOfExperience = 0;
    }
    
    /**
     * Constructs a new Teacher with complete information.
     * 
     * @param id Unique identifier for the teacher
     * @param name Full name of the teacher
     * @param email Email address of the teacher
     * @param password Encrypted password
     * @param subject Subject taught by the teacher
     * @param department Department the teacher belongs to
     * @param yearsOfExperience Years of teaching experience
     */
    public Teacher(String id, String name, String email, String password, 
                   String subject, String department, int yearsOfExperience) {
        super(id, name, email, password);
        this.subject = subject;
        this.department = department;
        this.yearsOfExperience = yearsOfExperience;
    }
    
    /**
     * Authenticates the teacher by comparing passwords.
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
     * @return "Teacher" as the role
     */
    @Override
    public String getRole() {
        return "Teacher";
    }
    
    /**
     * Performs teaching duties.
     * Implementation of Role interface.
     * 
     * @return A description of teaching duties performed
     */
    @Override
    public String performRoleDuties() {
        return "Teaching " + subject + ", recording attendance, and grading assignments.";
    }
    
    /**
     * Returns the role name.
     * Implementation of Role interface.
     * 
     * @return "Teacher"
     */
    @Override
    public String getRoleName() {
        return "Teacher";
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
        System.out.println("Teacher " + getName() + " sent notification to " + recipientId + ": " + message);
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
        System.out.println("Teacher " + getName() + " received: " + message);
    }
    
    /**
     * Gets the subject taught by the teacher.
     * 
     * @return The subject name
     */
    public String getSubject() {
        return subject;
    }
    
    /**
     * Sets the subject taught by the teacher.
     * 
     * @param subject The subject name
     */
    public void setSubject(String subject) {
        this.subject = subject;
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
     * Gets years of experience.
     * 
     * @return Years of teaching experience
     */
    public int getYearsOfExperience() {
        return yearsOfExperience;
    }
    
    /**
     * Sets years of experience.
     * 
     * @param yearsOfExperience Years of teaching experience
     */
    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }
}

