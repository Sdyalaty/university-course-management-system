package com.university.models;

/**
 * Student user class.
 * Demonstrates INHERITANCE and POLYMORPHISM.
 */
public class Student extends User {

    public Student(String userId, String username, String password,
                   String email, String fullName) {
        super(userId, username, password, email, fullName);
    }

    @Override
    public void displayMenu() {
        System.out.println("\n=== Student Menu ===");
        System.out.println("1. View Available Courses");
        System.out.println("2. Enroll in Course");
        System.out.println("3. Drop Course");
        System.out.println("4. View My Enrollments");
        System.out.println("5. View My Grades");
        System.out.println("6. Generate Transcript");
        System.out.println("7. Logout");
    }

    @Override
    public String getRole() {
        return "STUDENT";
    }

    @Override
    public void displayInfo() {
        System.out.println("Student: " + getFullName() + " (" + getUsername() + ")");
    }
}
