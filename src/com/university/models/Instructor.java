package com.university.models;

/**
 * Instructor user class.
 * Demonstrates INHERITANCE and POLYMORPHISM.
 */
public class Instructor extends User {

    public Instructor(String userId, String username, String password,
                      String email, String fullName) {
        super(userId, username, password, email, fullName);
    }

    @Override
    public void displayMenu() {
        System.out.println("\n=== Instructor Menu ===");
        System.out.println("1. View My Courses");
        System.out.println("2. View Course Roster");
        System.out.println("3. Assign Grades");
        System.out.println("4. Generate Teaching Load Report");
        System.out.println("5. Logout");
    }

    @Override
    public String getRole() {
        return "INSTRUCTOR";
    }

    @Override
    public void displayInfo() {
        System.out.println("Instructor: " + getFullName() + " (" + getUsername() + ")");
    }
}

