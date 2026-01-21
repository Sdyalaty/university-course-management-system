package com.university;

import com.university.auth.AuthenticationService;
import com.university.auth.AuthorizationService;
import com.university.auth.Permission;

import com.university.models.Admin;
import com.university.models.Instructor;
import com.university.models.Student;
import com.university.models.User;

import com.university.services.UserService;

import java.util.Scanner;

/**
 * Main entry point for the University Management System.
 * Demonstrates authentication, authorization, and file-based persistence.
 */
public class Main {

    private static final String USER_DATA_FILE = "users.dat";

    private static Scanner scanner = new Scanner(System.in);
    private static UserService userService = new UserService();
    private static AuthenticationService authService = new AuthenticationService(userService);
    private static AuthorizationService authorizationService = new AuthorizationService();

    public static void main(String[] args) {

        // ================================
        // Load users from file
        // ================================
        userService.loadFromFile(USER_DATA_FILE);

        // ================================
        // If no users, create sample users
        // ================================
        if (userService.getAllUsers().isEmpty()) {
            initSampleUsers();
        }

        // ================================
        // Main console loop
        // ================================
        boolean running = true;
        while (running) {

            if (!authService.isLoggedIn()) {
                showLoginMenu();
            } else {
                User currentUser = authService.getCurrentUser();
                switch (currentUser.getRole()) {
                    case "ADMIN":
                        showAdminMenu(currentUser);
                        break;
                    case "INSTRUCTOR":
                        showInstructorMenu(currentUser);
                        break;
                    case "STUDENT":
                        showStudentMenu(currentUser);
                        break;
                    default:
                        System.out.println("Unknown role. Logging out.");
                        authService.logout();
                }
            }
        }

        scanner.close();
    }

    // ================================
    // Sample users
    // ================================
    private static void initSampleUsers() {
        userService.addUser(new Admin("A001", "admin", "admin123", "admin@uni.com", "System Admin"));
        userService.addUser(new Instructor("I001", "john", "john123", "john@uni.com", "John Instructor"));
        userService.addUser(new Student("S001", "alice", "alice123", "alice@uni.com", "Alice Student"));
        userService.saveToFile(USER_DATA_FILE);
    }

    // ================================
    // Login menu
    // ================================
    private static void showLoginMenu() {
        System.out.println("\n=== Login Menu ===");
        System.out.println("1. Login");
        System.out.println("2. Exit");
        System.out.print("Choose an option: ");

        int choice = getIntInput();

        switch (choice) {
            case 1:
                performLogin();
                break;
            case 2:
                System.out.println("Goodbye!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    private static void performLogin() {
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        try {
            authService.login(username, password);
            User user = authService.getCurrentUser();
            System.out.println("\nLogin successful!");
            user.displayInfo();
            System.out.println("Role: " + user.getRole());
        } catch (IllegalArgumentException e) {
            System.out.println("Login failed: " + e.getMessage());
        }
    }

    // ================================
    // Admin menu
    // ================================
    private static void showAdminMenu(User admin) {
        admin.displayMenu();
        System.out.print("Choose an option: ");
        int choice = getIntInput();

        switch (choice) {
            case 6:
                authService.logout();
                System.out.println("Logged out successfully.");
                break;
            default:
                System.out.println("Feature not yet implemented.");
        }
    }

    // ================================
    // Instructor menu
    // ================================
    private static void showInstructorMenu(User instructor) {
        instructor.displayMenu();
        System.out.print("Choose an option: ");
        int choice = getIntInput();

        switch (choice) {
            case 5:
                authService.logout();
                System.out.println("Logged out successfully.");
                break;
            default:
                System.out.println("Feature not yet implemented.");
        }
    }

    // ================================
    // Student menu
    // ================================
    private static void showStudentMenu(User student) {
        student.displayMenu();
        System.out.print("Choose an option: ");
        int choice = getIntInput();

        switch (choice) {
            case 7:
                authService.logout();
                System.out.println("Logged out successfully.");
                break;
            default:
                System.out.println("Feature not yet implemented.");
        }
    }

    // ================================
    // Helper: integer input
    // ================================
    private static int getIntInput() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
