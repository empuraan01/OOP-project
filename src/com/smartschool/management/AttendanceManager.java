package com.smartschool.management;

import com.smartschool.exceptions.UnauthorizedAccessException;
import com.smartschool.users.User;
import java.io.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Manages student attendance records in the Smart School Management System.
 * This class demonstrates exception handling and file I/O operations.
 * 
 * <p>This class demonstrates:
 * <ul>
 *   <li>Exception handling (UnauthorizedAccessException)</li>
 *   <li>File I/O operations</li>
 * </ul>
 * 
 * @author Smart School Development Team
 * @version 1.0
 */
public class AttendanceManager {
    
    private Map<String, Map<LocalDate, Boolean>> attendance; // studentId -> (date -> present)
    private String filePath;
    
    /**
     * Constructs a new AttendanceManager.
     */
    public AttendanceManager() {
        this.attendance = new HashMap<>();
        this.filePath = "attendance.txt";
    }
    
    /**
     * Constructs a new AttendanceManager with a custom file path.
     * 
     * @param filePath The path to the attendance file
     */
    public AttendanceManager(String filePath) {
        this.attendance = new HashMap<>();
        this.filePath = filePath;
        loadFromFile();
    }
    
    /**
     * Records attendance for a student on a specific date.
     * 
     * @param studentId The ID of the student
     * @param date The date of attendance
     * @param isPresent true if present, false if absent
     * @param user The user performing the action (for authorization)
     * @throws UnauthorizedAccessException if user is not authorized
     */
    public void recordAttendance(String studentId, LocalDate date, boolean isPresent, User user) 
            throws UnauthorizedAccessException {
        checkAuthorization(user);
        attendance.computeIfAbsent(studentId, k -> new HashMap<>()).put(date, isPresent);
        saveToFile();
    }
    
    /**
     * Gets attendance status for a student on a specific date.
     * 
     * @param studentId The ID of the student
     * @param date The date to check
     * @return true if present, false if absent, null if not recorded
     */
    public Boolean getAttendance(String studentId, LocalDate date) {
        Map<LocalDate, Boolean> studentAttendance = attendance.get(studentId);
        if (studentAttendance == null) {
            return null;
        }
        return studentAttendance.get(date);
    }
    
    /**
     * Gets all attendance records for a student.
     * 
     * @param studentId The ID of the student
     * @return Map of dates to attendance status
     */
    public Map<LocalDate, Boolean> getStudentAttendance(String studentId) {
        Map<LocalDate, Boolean> studentAttendance = attendance.get(studentId);
        if (studentAttendance == null) {
            return new HashMap<>();
        }
        return new HashMap<>(studentAttendance);
    }
    
    /**
     * Calculates attendance percentage for a student.
     * 
     * @param studentId The ID of the student
     * @return The attendance percentage (0.0 to 100.0)
     */
    public Double calculateAttendancePercentage(String studentId) {
        Map<LocalDate, Boolean> studentAttendance = getStudentAttendance(studentId);
        if (studentAttendance.isEmpty()) {
            return Double.valueOf(0.0); // Wrapper class
        }
        
        int presentCount = 0;
        for (Boolean isPresent : studentAttendance.values()) {
            if (Boolean.TRUE.equals(isPresent)) { // Wrapper class comparison
                presentCount++;
            }
        }
        
        double percentage = (presentCount * 100.0) / studentAttendance.size();
        return Double.valueOf(percentage); // Wrapper class
    }
    
    /**
     * Checks if the user is authorized to record attendance.
     * Only Teachers and Admins can record attendance.
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
                "User " + user.getId() + " with role " + role + " is not authorized to record attendance.");
        }
    }
    
    /**
     * Saves attendance records to a file.
     * This demonstrates file I/O operations.
     */
    public void saveToFile() {
        if (filePath == null) {
            return;
        }
        
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (Map.Entry<String, Map<LocalDate, Boolean>> entry : attendance.entrySet()) {
                String studentId = entry.getKey();
                for (Map.Entry<LocalDate, Boolean> attendanceEntry : entry.getValue().entrySet()) {
                    writer.println(studentId + "," + attendanceEntry.getKey() + "," + attendanceEntry.getValue());
                }
            }
        } catch (IOException e) {
            System.err.println("Error saving attendance to file: " + e.getMessage());
        }
    }
    
    /**
     * Loads attendance records from a file.
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
                    LocalDate date = LocalDate.parse(parts[1].trim());
                    Boolean isPresent = Boolean.valueOf(parts[2].trim()); // Wrapper class
                    attendance.computeIfAbsent(studentId, k -> new HashMap<>()).put(date, isPresent);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading attendance from file: " + e.getMessage());
        }
    }
}

