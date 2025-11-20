package com.smartschool.users;

import com.smartschool.auth.PasswordUtils;
import com.smartschool.notifications.Notifier;

/**
 * Represents a Student user in the Smart School Management System.
 * Students can view their grades, attendance, and receive notifications.
 * 
 * <p>This class demonstrates:
 * <ul>
 *   <li>Hierarchical inheritance (extends User)</li>
 *   <li>Interface implementation (implements Notifier)</li>
 * </ul>
 * 
 * @author Smart School Development Team
 * @version 1.0
 */
public class Student extends User implements Notifier {
    
    private String gradeLevel;
    private String studentClass;
    private double gpa;
    
    /**
     * Constructs a new Student with basic information.
     * 
     * @param id Unique identifier for the student
     * @param name Full name of the student
     * @param email Email address of the student
     * @param password Encrypted password
     * @param gradeLevel Grade level of the student (e.g., "Grade 10")
     */
    public Student(String id, String name, String email, String password, String gradeLevel) {
        super(id, name, email, password);
        this.gradeLevel = gradeLevel;
        this.studentClass = "Not Assigned";
        this.gpa = 0.0;
    }
    
    /**
     * Constructs a new Student with complete information.
     * 
     * @param id Unique identifier for the student
     * @param name Full name of the student
     * @param email Email address of the student
     * @param password Encrypted password
     * @param gradeLevel Grade level of the student
     * @param studentClass Class assigned to the student
     * @param gpa Grade Point Average
     */
    public Student(String id, String name, String email, String password, 
                   String gradeLevel, String studentClass, double gpa) {
        super(id, name, email, password);
        this.gradeLevel = gradeLevel;
        this.studentClass = studentClass;
        this.gpa = gpa;
    }
    
    /**
     * Authenticates the student by comparing passwords.
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
     * @return "Student" as the role
     */
    @Override
    public String getRole() {
        return "Student";
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
        System.out.println("Student " + getName() + " sent notification to " + recipientId + ": " + message);
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
        System.out.println("Student " + getName() + " received: " + message);
    }
    
    /**
     * Gets the grade level.
     * 
     * @return The grade level
     */
    public String getGradeLevel() {
        return gradeLevel;
    }
    
    /**
     * Sets the grade level.
     * 
     * @param gradeLevel The grade level
     */
    public void setGradeLevel(String gradeLevel) {
        this.gradeLevel = gradeLevel;
    }
    
    /**
     * Gets the student's class.
     * 
     * @return The class name
     */
    public String getStudentClass() {
        return studentClass;
    }
    
    /**
     * Sets the student's class.
     * 
     * @param studentClass The class name
     */
    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }
    
    /**
     * Gets the GPA.
     * 
     * @return The Grade Point Average
     */
    public double getGpa() {
        return gpa;
    }
    
    /**
     * Sets the GPA.
     * 
     * @param gpa The Grade Point Average
     */
    public void setGpa(double gpa) {
        this.gpa = gpa;
    }
}

