package com.university;

import com.university.auth.AuthenticationService;
import java.util.Scanner;

/**
 * Main entry point for the University Management System.
 * 
 * TODO: Students should implement the menu system and integrate all components.
 */
public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static AuthenticationService authService = new AuthenticationService();
    
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("University Course Management System");
        System.out.println("========================================\n");
        
        // TODO: Initialize data repository
        // TODO: Load existing data
        
        boolean running = true;
        while (running) {
            if (!authService.isLoggedIn()) {
                showLoginMenu();
            } else {
                String role = authService.getCurrentUser().getRole();
                switch (role) {
                    case "ADMIN":
                        showAdminMenu();
                        break;
                    case "INSTRUCTOR":
                        showInstructorMenu();
                        break;
                    case "STUDENT":
                        showStudentMenu();
                        break;
                    default:
                        System.out.println("Unknown role. Logging out.");
                        authService.logout();
                }
            }
        }
        
        scanner.close();
    }
    
    /**
     * Display login menu.
     */
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
    
    /**
     * Perform login operation.
     */
    private static void performLogin() {
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        
        // TODO: Implement actual login with AuthenticationService
        authService.login(username, password);
        System.out.println("Login functionality not yet implemented.");
    }
    
    /**
     * Display admin menu.
     */
    private static void showAdminMenu() {
        System.out.println("\n=== Admin Menu ===");
        System.out.println("1. Manage Users");
        System.out.println("2. Manage Courses");
        System.out.println("3. View All Enrollments");
        System.out.println("4. Generate Reports");
        System.out.println("5. Send Announcements");
        System.out.println("6. Logout");
        System.out.print("Choose an option: ");
        
        int choice = getIntInput();
        
        // TODO: Implement admin menu options
        switch (choice) {
            case 6:
                authService.logout();
                System.out.println("Logged out successfully.");
                break;
            default:
                System.out.println("Feature not yet implemented.");
        }
    }
    
    /**
     * Display instructor menu.
     */
    private static void showInstructorMenu() {
        System.out.println("\n=== Instructor Menu ===");
        System.out.println("1. View My Courses");
        System.out.println("2. View Course Roster");
        System.out.println("3. Assign Grades");
        System.out.println("4. Generate Teaching Load Report");
        System.out.println("5. Logout");
        System.out.print("Choose an option: ");
        
        int choice = getIntInput();
        
        // TODO: Implement instructor menu options
        switch (choice) {
            case 5:
                authService.logout();
                System.out.println("Logged out successfully.");
                break;
            default:
                System.out.println("Feature not yet implemented.");
        }
    }
    
    /**
     * Display student menu.
     */
    private static void showStudentMenu() {
        System.out.println("\n=== Student Menu ===");
        System.out.println("1. View Available Courses");
        System.out.println("2. Enroll in Course");
        System.out.println("3. Drop Course");
        System.out.println("4. View My Enrollments");
        System.out.println("5. View My Grades");
        System.out.println("6. Generate Transcript");
        System.out.println("7. Logout");
        System.out.print("Choose an option: ");
        
        int choice = getIntInput();
        
        // TODO: Implement student menu options
        switch (choice) {
            case 7:
                authService.logout();
                System.out.println("Logged out successfully.");
                break;
            default:
                System.out.println("Feature not yet implemented.");
        }
    }
    
    /**
     * Helper method to get integer input with error handling.
     */
    private static int getIntInput() {
        try {
            int value = Integer.parseInt(scanner.nextLine());
            return value;
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
