package com.university.auth;

import com.university.models.User;
import com.university.services.UserService;

/**
 * Service for user authentication and session management.
 */
public class AuthenticationService {

    private final UserService userService;
    private User currentUser;

    public AuthenticationService(UserService userService) {
        this.userService = userService;
        this.currentUser = null;
    }

    public void login(String username, String password) {

        User user = userService.findByUsername(username);

        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("Invalid password");
        }

        currentUser = user;
    }

    public void logout() {
        currentUser = null;
    }

    public boolean isLoggedIn() {
        return currentUser != null;
    }

    public User getCurrentUser() {
        return currentUser;
    }
}
