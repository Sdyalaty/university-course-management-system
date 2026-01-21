package com.university.models;

/**
 * Admin user class.
 * Demonstrates INHERITANCE and POLYMORPHISM.
 */
public class Admin extends User {

    public Admin(String userId, String username, String password,
                 String email, String fullName) {
        super(userId, username, password, email, fullName);
    }

    @Override
    public void displayMenu() {
        System.out.println("\n=== Admin Menu ===");
        System.out.println("1. Manage Users");
        System.out.println("2. Manage Courses");
        System.out.println("3. View All Enrollments");
        System.out.println("4. Generate Reports");
        System.out.println("5. Send Announcements");
        System.out.println("6. Logout");
    }

    @Override
    public String getRole() {
        return "ADMIN";
    }

    @Override
    public void displayInfo() {
        System.out.println("Admin: " + getFullName() + " (" + getUsername() + ")");
    }
}
