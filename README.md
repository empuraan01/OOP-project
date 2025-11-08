# Smart School Management System

## 📚 Project Overview

The Smart School Management System is a comprehensive Java-based application designed to manage all aspects of a school's operations, including student enrollment, teacher management, attendance tracking, grade management, and administrative functions.

## 🏗️ Architecture

The system follows a modular architecture with clear separation of concerns:

```
com.smartschool
 ├── users/          # User hierarchy (Admin, Teacher, Student)
 ├── management/     # Business logic (Attendance, Grades, Schedule)
 ├── auth/           # Authentication and security
 ├── notifications/  # Messaging and notifications
 └── exceptions/     # Custom exception classes
```

## 🎯 Key Features

- **Role-Based Access Control**: Different access levels for Admin, Teacher, and Student
- **User Management**: Complete CRUD operations for all user types
- **Attendance Tracking**: Record and manage student attendance
- **Grade Management**: Store and retrieve student grades
- **Schedule Management**: Manage class schedules
- **Notification System**: Send messages and notices to users
- **Secure Authentication**: Password hashing and login validation

## 🚀 Getting Started

### Prerequisites

- Java 17 or higher
- A Java IDE (IntelliJ IDEA, Eclipse, or VS Code)

### Compilation

```bash
javac -d out src/com/smartschool/**/*.java src/com/smartschool/*.java
```

### Execution

```bash
java -cp out com.smartschool.Main
```

## 📖 Usage

1. Run the `Main.java` class
2. Follow the interactive menu to:
   - Login as Admin, Teacher, or Student
   - Manage users (Admin only)
   - Record attendance (Teachers)
   - View grades (Students and Teachers)
   - Send notifications
   - Manage schedules

## 🧩 OOP Features Implemented

This project demonstrates the following Object-Oriented Programming concepts:

- ✅ Abstract classes
- ✅ Interfaces and multiple inheritance
- ✅ Hierarchical inheritance
- ✅ Nested classes (static)
- ✅ Method overloading
- ✅ Constructor overloading
- ✅ Varargs
- ✅ Exception handling
- ✅ File I/O operations
- ✅ Wrapper classes
- ✅ Encapsulation
- ✅ Polymorphism

See `rubric_table.txt` for detailed mapping of each requirement to code locations.

## 📝 Project Structure

```
src/
└── com/
    └── smartschool/
        ├── users/
        │   ├── User.java (abstract)
        │   ├── Admin.java
        │   ├── Teacher.java
        │   └── Student.java
        ├── management/
        │   ├── AttendanceManager.java
        │   ├── GradeManager.java
        │   └── ScheduleManager.java
        ├── auth/
        │   ├── LoginSystem.java
        │   └── PasswordUtils.java
        ├── notifications/
        │   ├── NoticeBoard.java
        │   └── Message.java
        ├── exceptions/
        │   ├── InvalidLoginException.java
        │   └── UnauthorizedAccessException.java
        └── Main.java
```

## 👥 Authors

Padmanabhan, Sanjoe, Mathew, Ashish



