package com.smartschool.management;

import com.smartschool.exceptions.UnauthorizedAccessException;
import com.smartschool.users.User;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Manages student grades in the Smart School Management System.
 * This class demonstrates method overloading, constructor overloading, varargs,
 * exception handling, file I/O operations, and wrapper class usage.
 * 
 * <p>This class demonstrates:
 * <ul>
 *   <li>Method overloading (addGrade methods)</li>
 *   <li>Constructor overloading</li>
 *   <li>Varargs (addMultipleGrades)</li>
 *   <li>Exception handling (UnauthorizedAccessException)</li>
 *   <li>File I/O operations</li>
 *   <li>Wrapper class usage (Double, Integer)</li>
 * </ul>
 * 
 * @author Smart School Development Team
 * @version 1.0
 */
public class GradeManager {
    
    private Map<String, Map<String, Double>> grades; // studentId -> (course -> grade)
    private String filePath;
    
    /**
     * Default constructor.
     * Initializes grade storage in memory only.
     */
    public GradeManager() {
        this.grades = new HashMap<>();
        this.filePath = null;
    }
    
    /**
     * Constructor with file path for persistence.
     * This demonstrates constructor overloading.
     * 
     * @param filePath The path to the file for storing grades
     */
    public GradeManager(String filePath) {
        this.grades = new HashMap<>();
        this.filePath = filePath;
        loadFromFile();
    }
    
    /**
     * Adds a grade for a student in a course using Double wrapper class.
     * This demonstrates method overloading.
     * 
     * @param studentId The ID of the student
     * @param course The course name
     * @param grade The grade as a Double (wrapper class)
     * @param user The user performing the action (for authorization)
     * @throws UnauthorizedAccessException if user is not authorized
     */
    public void addGrade(String studentId, String course, Double grade, User user) 
            throws UnauthorizedAccessException {
        checkAuthorization(user);
        grades.computeIfAbsent(studentId, k -> new HashMap<>()).put(course, grade);
        if (filePath != null) {
            saveToFile();
        }
    }
    
    /**
     * Adds a grade for a student in a course using Integer wrapper class.
     * This demonstrates method overloading with different parameter types.
     * 
     * @param studentId The ID of the student
     * @param course The course name
     * @param grade The grade as an Integer (wrapper class)
     * @param user The user performing the action (for authorization)
     * @throws UnauthorizedAccessException if user is not authorized
     */
    public void addGrade(String studentId, String course, Integer grade, User user) 
            throws UnauthorizedAccessException {
        checkAuthorization(user);
        // Convert Integer to Double
        Double doubleGrade = Double.valueOf(grade.doubleValue()); // Wrapper class conversion
        grades.computeIfAbsent(studentId, k -> new HashMap<>()).put(course, doubleGrade);
        if (filePath != null) {
            saveToFile();
        }
    }
    
    /**
     * Adds grades for multiple courses using varargs.
     * This demonstrates varargs overloading.
     * 
     * @param studentId The ID of the student
     * @param user The user performing the action
     * @param courses Variable number of course names
     * @throws UnauthorizedAccessException if user is not authorized
     */
    public void addMultipleGrades(String studentId, User user, String... courses) 
            throws UnauthorizedAccessException {
        checkAuthorization(user);
        Map<String, Double> studentGrades = grades.computeIfAbsent(studentId, k -> new HashMap<>());
        for (String course : courses) {
            // Initialize with default grade of 0.0
            studentGrades.putIfAbsent(course, Double.valueOf(0.0)); // Wrapper class usage
        }
        if (filePath != null) {
            saveToFile();
        }
    }
    
    /**
     * Gets the grade for a student in a specific course.
     * 
     * @param studentId The ID of the student
     * @param course The course name
     * @return The grade, or null if not found
     */
    public Double getGrade(String studentId, String course) {
        Map<String, Double> studentGrades = grades.get(studentId);
        if (studentGrades == null) {
            return null;
        }
        return studentGrades.get(course);
    }
    
    /**
     * Gets all grades for a specific student.
     * 
     * @param studentId The ID of the student
     * @return Map of course names to grades
     */
    public Map<String, Double> getStudentGrades(String studentId) {
        Map<String, Double> studentGrades = grades.get(studentId);
        if (studentGrades == null) {
            return new HashMap<>();
        }
        return new HashMap<>(studentGrades);
    }
    
    /**
     * Calculates the GPA for a student.
     * 
     * @param studentId The ID of the student
     * @return The GPA, or 0.0 if no grades exist
     */
    public Double calculateGPA(String studentId) {
        Map<String, Double> studentGrades = getStudentGrades(studentId);
        if (studentGrades.isEmpty()) {
            return Double.valueOf(0.0); // Wrapper class
        }
        
        double sum = 0.0;
        for (Double grade : studentGrades.values()) {
            sum += grade.doubleValue();
        }
        return Double.valueOf(sum / studentGrades.size()); // Wrapper class
    }
    
    /**
     * Checks if the user is authorized to modify grades.
     * Only Teachers and Admins can modify grades.
     * 
     * @param user The user to check
     * @throws UnauthorizedAccessException if user is not authorized
     */
    private void checkAuthorization(User user) throws UnauthorizedAccessException {
        if (user == null) {
            throw new UnauthorizedAccessException("No user logged in.");
        }
        String role = user.getRole();
        if (!role.equals("Teacher") && !role.equals("Admin")) {
            throw new UnauthorizedAccessException(
                "User " + user.getId() + " with role " + role + " is not authorized to modify grades.");
        }
    }
    
    /**
     * Saves grades to a file.
     * This demonstrates file I/O operations.
     */
    public void saveToFile() {
        if (filePath == null) {
            return;
        }
        
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (Map.Entry<String, Map<String, Double>> entry : grades.entrySet()) {
                String studentId = entry.getKey();
                for (Map.Entry<String, Double> gradeEntry : entry.getValue().entrySet()) {
                    writer.println(studentId + "," + gradeEntry.getKey() + "," + gradeEntry.getValue());
                }
            }
        } catch (IOException e) {
            System.err.println("Error saving grades to file: " + e.getMessage());
        }
    }
    
    /**
     * Loads grades from a file.
     * This demonstrates file I/O operations.
     */
    public void loadFromFile() {
        if (filePath == null) {
            return;
        }
        
        File file = new File(filePath);
        if (!file.exists()) {
            return;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String studentId = parts[0].trim();
                    String course = parts[1].trim();
                    Double grade = Double.valueOf(parts[2].trim()); // Wrapper class
                    grades.computeIfAbsent(studentId, k -> new HashMap<>()).put(course, grade);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading grades from file: " + e.getMessage());
        }
    }
}

