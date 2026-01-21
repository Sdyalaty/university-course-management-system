package com.university;

import com.university.auth.AuthenticationService;
import com.university.auth.AuthorizationService;
import com.university.auth.Permission;

import com.university.models.Admin;
import com.university.models.Instructor;
import com.university.models.Student;
import com.university.models.User;

import com.university.services.UserService;
import com.university.services.CourseService;

import java.util.Scanner;

/**
 * Main entry point for the University Management System.
 * Demonstrates authentication, authorization, user management, and course management.
 */
public class Main {

    private static final String USER_DATA_FILE = "users.dat";

    private static Scanner scanner = new Scanner(System.in);
    private static UserService userService = new UserService();
    private static AuthenticationService authService = new AuthenticationService(userService);
    private static AuthorizationService authorizationService = new AuthorizationService();
    private static CourseService courseService = new CourseService();

    public static void main(String[] args) {

        // Load users from file
        userService.loadFromFile(USER_DATA_FILE);

        // If no users, create sample users
        if (userService.getAllUsers().isEmpty()) {
            initSampleUsers();
        }

        // Main console loop
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
        userService.addUser(new Instructor("I001", "prof1", "prof123", "prof1@uni.com", "John Instructor"));
        userService.addUser(new Student("S001", "student1", "student123", "student1@uni.com", "Alice Student"));
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
            case 1:
                manageUsers(); // Your user management code
                break;
            case 2:
                courseService.manageCourses(); // Teammate's course management code
                break;
            case 3:
                System.out.println("View All Enrollments - Feature not yet implemented.");
                break;
            case 4:
                System.out.println("Generate Reports - Feature not yet implemented.");
                break;
            case 5:
                System.out.println("Send Announcements - Feature not yet implemented.");
                break;
            case 6:
                authService.logout();
                System.out.println("Logged out successfully.");
                break;
            default:
                System.out.println("Invalid option. Please try again.");
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
    // User Management for Admin
    // ================================
    private static void manageUsers() {
        System.out.println("\n=== User Management ===");
        System.out.println("1. Create User");
        System.out.println("2. View Users");
        System.out.println("3. Back to Admin Menu");
        System.out.print("Choose an option: ");

        int choice = getIntInput();

        switch (choice) {
            case 1:
                createUser();
                break;
            case 2:
                viewUsers();
                break;
            case 3:
                return;
            default:
                System.out.println("Invalid option. Returning to Admin Menu.");
        }
    }

    private static void createUser() {
        System.out.print("Enter role (ADMIN / INSTRUCTOR / STUDENT): ");
        String role = scanner.nextLine().toUpperCase();
        System.out.print("Enter userId: ");
        String userId = scanner.nextLine();
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter full name: ");
        String fullName = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        User user;
        switch (role) {
            case "ADMIN":
                user = new Admin(userId, username, password, email, fullName);
                break;
            case "INSTRUCTOR":
                user = new Instructor(userId, username, password, email, fullName);
                break;
            case "STUDENT":
                user = new Student(userId, username, password, email, fullName);
                break;
            default:
                System.out.println("Invalid role. User not created.");
                return;
        }

        userService.addUser(user);
        userService.saveToFile(USER_DATA_FILE);
        System.out.println(role + " user created successfully!");
    }

    private static void viewUsers() {
        System.out.println("\n=== All Users ===");
        for (User user : userService.getAllUsers()) {
            user.displayInfo();
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
