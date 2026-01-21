package com.university;

import com.university.auth.AuthenticationService;
import com.university.auth.AuthorizationService;
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

    // ================================
    // Perform login
    // ================================
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
            case 1: // Manage Users
                manageUsers();
                break;
            case 6:
                authService.logout();
                System.out.println("Logged out successfully.");
                break;
            default:
                System.out.println("Feature not yet implemented.");
        }
    }

    // ================================
    // Manage Users (Admin)
    // ================================
    private static void manageUsers() {
        System.out.println("\n=== Manage Users ===");
        System.out.println("1. Create User");
        System.out.println("2. View All Users");
        System.out.println("3. Edit User");
        System.out.println("4. Delete User");
        System.out.println("5. Back to Admin Menu");
        System.out.print("Choose an option: ");

        int choice = getIntInput();

        switch (choice) {
            case 1:
                createUser();
                break;
            case 2:
                listAllUsers();
                break;
            case 3:
                editUser();
                break;
            case 4:
                deleteUser();
                break;
            case 5:
                return; // Back to Admin menu
            default:
                System.out.println("Invalid option. Try again.");
        }
    }

    // ================================
    // Create a new user (Admin)
    // ================================
    private static void createUser() {
        System.out.println("\n=== Create User ===");
        System.out.print("Enter user type (ADMIN / INSTRUCTOR / STUDENT): ");
        String type = scanner.nextLine().toUpperCase();

        System.out.print("User ID: ");
        String userId = scanner.nextLine();
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Full Name: ");
        String fullName = scanner.nextLine();

        User newUser;
        switch (type) {
            case "ADMIN":
                newUser = new Admin(userId, username, password, email, fullName);
                break;
            case "INSTRUCTOR":
                newUser = new Instructor(userId, username, password, email, fullName);
                break;
            case "STUDENT":
                newUser = new Student(userId, username, password, email, fullName);
                break;
            default:
                System.out.println("Invalid user type.");
                return;
        }

        userService.addUser(newUser);
        userService.saveToFile(USER_DATA_FILE);
        System.out.println(type + " user created successfully!");
    }

    // ================================
    // List all users (Admin)
    // ================================
    private static void listAllUsers() {
        System.out.println("\n=== All Users ===");
        for (User user : userService.getAllUsers()) {
            System.out.println(user);
        }
    }

    // ================================
    // Edit user (Admin)
    // ================================
    private static void editUser() {
        System.out.print("\nEnter the username of the user to edit: ");
        String username = scanner.nextLine();

        User user = userService.findByUsername(username);
        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        System.out.println("Editing user: " + user);
        System.out.print("New Username (leave blank to keep current): ");
        String newUsername = scanner.nextLine();
        if (!newUsername.isBlank()) user.setUsername(newUsername);

        System.out.print("New Password (leave blank to keep current): ");
        String newPassword = scanner.nextLine();
        if (!newPassword.isBlank()) user.setPassword(newPassword);

        System.out.print("New Email (leave blank to keep current): ");
        String newEmail = scanner.nextLine();
        if (!newEmail.isBlank()) user.setEmail(newEmail);

        System.out.print("New Full Name (leave blank to keep current): ");
        String newFullName = scanner.nextLine();
        if (!newFullName.isBlank()) user.setFullName(newFullName);

        userService.saveToFile(USER_DATA_FILE);
        System.out.println("User updated successfully!");
    }

    // ================================
    // Delete user (Admin)
    // ================================
    private static void deleteUser() {
        System.out.print("\nEnter the username of the user to delete: ");
        String username = scanner.nextLine();

        User user = userService.findByUsername(username);
        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        System.out.print("Are you sure you want to delete " + user.getUsername() + "? (y/n): ");
        String confirm = scanner.nextLine().toLowerCase();
        if (confirm.equals("y")) {
            userService.getAllUsers().remove(user);
            userService.saveToFile(USER_DATA_FILE);
            System.out.println("User deleted successfully!");
        } else {
            System.out.println("Deletion cancelled.");
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
