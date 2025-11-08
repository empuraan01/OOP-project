package com.smartschool;

import com.smartschool.auth.LoginSystem;
import com.smartschool.auth.PasswordUtils;
import com.smartschool.exceptions.UnauthorizedAccessException;
import com.smartschool.management.AttendanceManager;
import com.smartschool.management.GradeManager;
import com.smartschool.management.ScheduleManager;
import com.smartschool.notifications.NoticeBoard;
import com.smartschool.users.Admin;
import com.smartschool.users.Student;
import com.smartschool.users.Teacher;
import com.smartschool.users.User;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * Main entry point for the Smart School Management System.
 * This class demonstrates the integration of all system components and
 * provides an interactive menu-driven interface.
 * 
 * <p>This class demonstrates:
 * <ul>
 *   <li>Scanner usage for user input (I/O operations)</li>
 *   <li>Exception handling</li>
 *   <li>Integration of all system components</li>
 * </ul>
 * 
 * @author Smart School Development Team
 * @version 1.0
 */
public class Main {
    
    private static LoginSystem loginSystem;
    private static GradeManager gradeManager;
    private static AttendanceManager attendanceManager;
    private static ScheduleManager scheduleManager;
    private static NoticeBoard noticeBoard;
    private static Scanner scanner;
    
    /**
     * Main method that initializes the system and starts the application.
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("  Smart School Management System");
        System.out.println("========================================\n");
        
        initializeSystem();
        initializeSampleData();
        
        scanner = new Scanner(System.in);
        
        // Main application loop
        boolean running = true;
        while (running) {
            if (!loginSystem.isLoggedIn()) {
                running = handleLogin();
            } else {
                running = handleMainMenu();
            }
        }
        
        scanner.close();
        System.out.println("\nThank you for using Smart School Management System!");
    }
    
    /**
     * Initializes all system components.
     */
    private static void initializeSystem() {
        loginSystem = new LoginSystem();
        gradeManager = new GradeManager("grades.txt");
        attendanceManager = new AttendanceManager("attendance.txt");
        scheduleManager = new ScheduleManager();
        noticeBoard = new NoticeBoard();
    }
    
    /**
     * Initializes sample data for demonstration purposes.
     */
    private static void initializeSampleData() {
        // Create sample users with hashed passwords
        String adminPassword = PasswordUtils.PasswordHasher.hashPassword("admin123");
        Admin admin = new Admin("ADM001", "John Admin", "admin@school.edu", adminPassword, "IT");
        loginSystem.registerUser(admin);
        
        String teacherPassword = PasswordUtils.PasswordHasher.hashPassword("teacher123");
        Teacher teacher = new Teacher("TCH001", "Jane Teacher", "teacher@school.edu", 
                                     teacherPassword, "Mathematics", "Science", 5);
        loginSystem.registerUser(teacher);
        
        String studentPassword = PasswordUtils.PasswordHasher.hashPassword("student123");
        Student student = new Student("STU001", "Bob Student", "student@school.edu", 
                                     studentPassword, "Grade 10", "10A", 3.5);
        loginSystem.registerUser(student);
        
        System.out.println("Sample users created:");
        System.out.println("  Admin: ADM001 / admin123");
        System.out.println("  Teacher: TCH001 / teacher123");
        System.out.println("  Student: STU001 / student123\n");
    }
    
    /**
     * Handles the login process.
     * 
     * @return true to continue, false to exit
     */
    private static boolean handleLogin() {
        System.out.println("\n=== Login ===");
        System.out.println("1. Login");
        System.out.println("2. Exit");
        System.out.print("Choose an option: ");
        
        String choice = scanner.nextLine().trim();
        
        if (choice.equals("1")) {
            User user = loginSystem.interactiveLogin(scanner);
            if (user != null) {
                System.out.println("\nLogin successful! Welcome, " + user.getName() + " (" + user.getRole() + ")");
            }
        } else if (choice.equals("2")) {
            return false;
        } else {
            System.out.println("Invalid option. Please try again.");
        }
        
        return true;
    }
    
    /**
     * Handles the main menu based on user role.
     * 
     * @return true to continue, false to exit
     */
    private static boolean handleMainMenu() {
        User currentUser = loginSystem.getCurrentUser();
        String role = currentUser.getRole();
        
        System.out.println("\n=== Main Menu ===");
        System.out.println("Logged in as: " + currentUser.getName() + " (" + role + ")");
        System.out.println("1. View Profile");
        System.out.println("2. Manage Grades");
        System.out.println("3. Manage Attendance");
        System.out.println("4. View Notifications");
        System.out.println("5. Send Notification");
        System.out.println("5a. View Schedule");
        
        if (role.equals("Admin")) {
            System.out.println("6. Admin Functions");
        } else if (role.equals("Teacher")) {
            System.out.println("6. Teacher Functions");
        } else {
            System.out.println("6. Student Functions");
        }
        
        System.out.println("7. Logout");
        System.out.println("8. Exit");
        System.out.print("Choose an option: ");
        
        String choice = scanner.nextLine().trim();
        
        switch (choice) {
            case "1":
                viewProfile(currentUser);
                break;
            case "2":
                handleGradesMenu(currentUser);
                break;
            case "3":
                handleAttendanceMenu(currentUser);
                break;
            case "4":
                viewNotifications(currentUser);
                break;
            case "5":
                sendNotification(currentUser);
                break;
            case "5a":
                viewSchedule();
                break;
            case "6":
                if (role.equals("Admin")) {
                    handleAdminMenu(currentUser);
                } else if (role.equals("Teacher")) {
                    handleTeacherMenu(currentUser);
                } else {
                    handleStudentMenu(currentUser);
                }
                break;
            case "7":
                loginSystem.logout();
                System.out.println("Logged out successfully.");
                break;
            case "8":
                return false;
            default:
                System.out.println("Invalid option. Please try again.");
        }
        
        return true;
    }
    
    /**
     * Displays user profile information.
     * 
     * @param user The user whose profile to display
     */
    private static void viewProfile(User user) {
        System.out.println("\n=== Profile ===");
        System.out.println("ID: " + user.getId());
        System.out.println("Name: " + user.getName());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Role: " + user.getRole());
        
        if (user instanceof Admin) {
            Admin admin = (Admin) user;
            System.out.println("Department: " + admin.getDepartment());
            System.out.println("Access Level: " + admin.getAccessLevel());
        } else if (user instanceof Teacher) {
            Teacher teacher = (Teacher) user;
            System.out.println("Subject: " + teacher.getSubject());
            System.out.println("Department: " + teacher.getDepartment());
            System.out.println("Experience: " + teacher.getYearsOfExperience() + " years");
        } else if (user instanceof Student) {
            Student student = (Student) user;
            System.out.println("Grade Level: " + student.getGradeLevel());
            System.out.println("Class: " + student.getStudentClass());
            System.out.println("GPA: " + student.getGpa());
        }
    }
    
    /**
     * Handles the grades menu.
     * 
     * @param user The current user
     */
    private static void handleGradesMenu(User user) {
        System.out.println("\n=== Grades Management ===");
        System.out.println("1. Add Grade");
        System.out.println("2. View Grade");
        System.out.println("3. View All Grades (Student)");
        System.out.println("4. Calculate GPA");
        System.out.print("Choose an option: ");
        
        String choice = scanner.nextLine().trim();
        
        try {
            switch (choice) {
                case "1":
                    System.out.print("Enter Student ID: ");
                    String studentId = scanner.nextLine().trim();
                    System.out.print("Enter Course: ");
                    String course = scanner.nextLine().trim();
                    System.out.print("Enter Grade (as number): ");
                    String gradeInput = scanner.nextLine().trim();
                    
                    try {
                        // Try parsing as Double first (method overloading)
                        Double grade = Double.valueOf(gradeInput);
                        gradeManager.addGrade(studentId, course, grade, user);
                        System.out.println("Grade added successfully!");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid grade format.");
                    }
                    break;
                case "2":
                    System.out.print("Enter Student ID: ");
                    studentId = scanner.nextLine().trim();
                    System.out.print("Enter Course: ");
                    course = scanner.nextLine().trim();
                    Double grade = gradeManager.getGrade(studentId, course);
                    if (grade != null) {
                        System.out.println("Grade: " + grade);
                    } else {
                        System.out.println("Grade not found.");
                    }
                    break;
                case "3":
                    if (user instanceof Student) {
                        Student student = (Student) user;
                        var grades = gradeManager.getStudentGrades(student.getId());
                        System.out.println("\nYour Grades:");
                        for (var entry : grades.entrySet()) {
                            System.out.println("  " + entry.getKey() + ": " + entry.getValue());
                        }
                    } else {
                        System.out.print("Enter Student ID: ");
                        studentId = scanner.nextLine().trim();
                        var grades = gradeManager.getStudentGrades(studentId);
                        System.out.println("\nGrades for " + studentId + ":");
                        for (var entry : grades.entrySet()) {
                            System.out.println("  " + entry.getKey() + ": " + entry.getValue());
                        }
                    }
                    break;
                case "4":
                    if (user instanceof Student) {
                        Student student = (Student) user;
                        Double gpa = gradeManager.calculateGPA(student.getId());
                        System.out.println("GPA: " + gpa);
                    } else {
                        System.out.print("Enter Student ID: ");
                        studentId = scanner.nextLine().trim();
                        Double gpa = gradeManager.calculateGPA(studentId);
                        System.out.println("GPA: " + gpa);
                    }
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } catch (UnauthorizedAccessException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    /**
     * Handles the attendance menu.
     * 
     * @param user The current user
     */
    private static void handleAttendanceMenu(User user) {
        System.out.println("\n=== Attendance Management ===");
        System.out.println("1. Record Attendance");
        System.out.println("2. View Attendance");
        System.out.println("3. View Attendance Percentage");
        System.out.print("Choose an option: ");
        
        String choice = scanner.nextLine().trim();
        
        try {
            switch (choice) {
                case "1":
                    System.out.print("Enter Student ID: ");
                    String studentId = scanner.nextLine().trim();
                    System.out.print("Present? (y/n): ");
                    String presentInput = scanner.nextLine().trim().toLowerCase();
                    boolean isPresent = presentInput.equals("y") || presentInput.equals("yes");
                    
                    attendanceManager.recordAttendance(studentId, LocalDate.now(), isPresent, user);
                    System.out.println("Attendance recorded successfully!");
                    break;
                case "2":
                    if (user instanceof Student) {
                        Student student = (Student) user;
                        var attendance = attendanceManager.getStudentAttendance(student.getId());
                        System.out.println("\nYour Attendance:");
                        for (var entry : attendance.entrySet()) {
                            System.out.println("  " + entry.getKey() + ": " + 
                                            (entry.getValue() ? "Present" : "Absent"));
                        }
                    } else {
                        System.out.print("Enter Student ID: ");
                        studentId = scanner.nextLine().trim();
                        var attendance = attendanceManager.getStudentAttendance(studentId);
                        System.out.println("\nAttendance for " + studentId + ":");
                        for (var entry : attendance.entrySet()) {
                            System.out.println("  " + entry.getKey() + ": " + 
                                            (entry.getValue() ? "Present" : "Absent"));
                        }
                    }
                    break;
                case "3":
                    if (user instanceof Student) {
                        Student student = (Student) user;
                        Double percentage = attendanceManager.calculateAttendancePercentage(student.getId());
                        System.out.println("Attendance Percentage: " + percentage + "%");
                    } else {
                        System.out.print("Enter Student ID: ");
                        studentId = scanner.nextLine().trim();
                        Double percentage = attendanceManager.calculateAttendancePercentage(studentId);
                        System.out.println("Attendance Percentage: " + percentage + "%");
                    }
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } catch (UnauthorizedAccessException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    /**
     * Displays notifications for the current user.
     * 
     * @param user The current user
     */
    private static void viewNotifications(User user) {
        System.out.println("\n=== Notifications ===");
        var messages = noticeBoard.getMessagesFor(user.getId());
        if (messages.isEmpty()) {
            System.out.println("No notifications.");
        } else {
            for (var message : messages) {
                System.out.println(message);
            }
        }
    }
    
    /**
     * Handles sending notifications.
     * 
     * @param user The current user
     */
    private static void sendNotification(User user) {
        System.out.println("\n=== Send Notification ===");
        System.out.print("Enter Recipient ID: ");
        String recipientId = scanner.nextLine().trim();
        System.out.print("Enter Message: ");
        String message = scanner.nextLine().trim();
        
        noticeBoard.sendMessage(user.getId(), recipientId, message);
        System.out.println("Notification sent successfully!");
    }
    
    /**
     * Handles admin-specific menu options.
     * 
     * @param user The admin user
     */
    private static void handleAdminMenu(User user) {
        System.out.println("\n=== Admin Functions ===");
        System.out.println("1. View All Users");
        System.out.println("2. Broadcast Message (Varargs Demo)");
        System.out.print("Choose an option: ");
        
        String choice = scanner.nextLine().trim();
        
        switch (choice) {
            case "1":
                System.out.println("\nAll Registered Users:");
                var users = loginSystem.getUsers();
                for (var entry : users.entrySet()) {
                    System.out.println("  " + entry.getValue());
                }
                break;
            case "2":
                System.out.print("Enter Message: ");
                String message = scanner.nextLine().trim();
                System.out.print("Enter Recipient IDs (comma-separated): ");
                String recipients = scanner.nextLine().trim();
                String[] recipientIds = recipients.split(",");
                // Trim each ID
                for (int i = 0; i < recipientIds.length; i++) {
                    recipientIds[i] = recipientIds[i].trim();
                }
                // Varargs demonstration
                int count = noticeBoard.broadcastMessage(user.getId(), message, recipientIds);
                System.out.println("Message broadcasted to " + count + " recipients.");
                break;
            default:
                System.out.println("Invalid option.");
        }
    }
    
    /**
     * Handles teacher-specific menu options.
     * 
     * @param user The teacher user
     */
    private static void handleTeacherMenu(User user) {
        System.out.println("\n=== Teacher Functions ===");
        System.out.println("1. Perform Role Duties");
        System.out.println("2. Add Multiple Courses (Varargs Demo)");
        System.out.print("Choose an option: ");
        
        String choice = scanner.nextLine().trim();
        
        try {
            switch (choice) {
                case "1":
                    if (user instanceof Teacher) {
                        Teacher teacher = (Teacher) user;
                        System.out.println(teacher.performRoleDuties());
                    }
                    break;
                case "2":
                    System.out.print("Enter Student ID: ");
                    String studentId = scanner.nextLine().trim();
                    System.out.print("Enter Course Names (comma-separated): ");
                    String courses = scanner.nextLine().trim();
                    String[] courseArray = courses.split(",");
                    // Trim each course
                    for (int i = 0; i < courseArray.length; i++) {
                        courseArray[i] = courseArray[i].trim();
                    }
                    // Varargs demonstration
                    gradeManager.addMultipleGrades(studentId, user, courseArray);
                    System.out.println("Courses added successfully!");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } catch (UnauthorizedAccessException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    /**
     * Displays class schedules.
     */
    private static void viewSchedule() {
        System.out.println("\n=== Class Schedules ===");
        var schedules = scheduleManager.getAllSchedules();
        if (schedules.isEmpty()) {
            System.out.println("No schedules available.");
        } else {
            for (var entry : schedules.entrySet()) {
                System.out.println("Class: " + entry.getKey());
                for (var scheduleEntry : entry.getValue().entrySet()) {
                    System.out.println("  " + scheduleEntry.getKey() + ": " + scheduleEntry.getValue());
                }
            }
        }
    }
    
    /**
     * Handles student-specific menu options.
     * 
     * @param user The student user
     */
    private static void handleStudentMenu(User user) {
        System.out.println("\n=== Student Functions ===");
        System.out.println("1. View My Grades");
        System.out.println("2. View My Attendance");
        System.out.print("Choose an option: ");
        
        String choice = scanner.nextLine().trim();
        
        if (user instanceof Student) {
            Student student = (Student) user;
            switch (choice) {
                case "1":
                    var grades = gradeManager.getStudentGrades(student.getId());
                    System.out.println("\nYour Grades:");
                    if (grades.isEmpty()) {
                        System.out.println("  No grades recorded yet.");
                    } else {
                        for (var entry : grades.entrySet()) {
                            System.out.println("  " + entry.getKey() + ": " + entry.getValue());
                        }
                    }
                    break;
                case "2":
                    var attendance = attendanceManager.getStudentAttendance(student.getId());
                    System.out.println("\nYour Attendance:");
                    if (attendance.isEmpty()) {
                        System.out.println("  No attendance records yet.");
                    } else {
                        for (var entry : attendance.entrySet()) {
                            System.out.println("  " + entry.getKey() + ": " + 
                                            (entry.getValue() ? "Present" : "Absent"));
                        }
                        Double percentage = attendanceManager.calculateAttendancePercentage(student.getId());
                        System.out.println("  Overall: " + percentage + "%");
                    }
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}

