package com.university.services;

import com.university.models.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Service for managing users in the University Management System.
 * Demonstrates ENCAPSULATION, ABSTRACTION, and DATA PERSISTENCE.
 */
public class UserService {

    private static final String USER_FILE = "users.dat";
    private final List<User> users;

    public UserService() {
        this.users = new ArrayList<>();
        loadFromFile(USER_FILE);

        // Initialize default users only if none exist
        if (users.isEmpty()) {
            initializeDefaultUsers();
            saveToFile(USER_FILE);
        }
    }

    /**
     * Create default system users.
     */
    private void initializeDefaultUsers() {

        users.add(new Admin(
                "A001",
                "admin",
                "admin123",
                "admin@university.com",
                "System Administrator"
        ));

        users.add(new Instructor(
                "I001",
                "prof1",
                "prof123",
                "prof1@university.com",
                "Prof. John Doe"
        ));

        users.add(new Student(
                "S001",
                "student1",
                "student123",
                "student1@university.com",
                "Jane Student"
        ));
    }

    /**
     * Add a user to the system.
     *
     * @param user User object to add
     */
    public void addUser(User user) {
        if (user != null) {
            users.add(user);
            saveToFile(USER_FILE);
        }
    }

    /**
     * Find a user by username.
     *
     * @param username Username to search for
     * @return User object if found, null otherwise
     */
    public User findByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Get all users in the system.
     *
     * @return List of all users
     */
    public List<User> getAllUsers() {
        return users;
    }

    // ===============================
    // File-based persistence methods
    // ===============================

    /**
     * Save users to a file using serialization.
     *
     * @param filename file path
     */
    public void saveToFile(String filename) {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(users);
        } catch (IOException e) {
            System.out.println("Error saving users: " + e.getMessage());
        }
    }

    /**
     * Load users from a file using serialization.
     *
     * @param filename file path
     */
    @SuppressWarnings("unchecked")
    public void loadFromFile(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            return; // No file to load
        }

        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(filename))) {
            List<User> loadedUsers = (List<User>) ois.readObject();
            users.clear();
            users.addAll(loadedUsers);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading users: " + e.getMessage());
        }
    }
}
