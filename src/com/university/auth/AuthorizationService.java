package com.university.auth;

import com.university.models.User;

/**
 * Handles role-based authorization logic.
 * Demonstrates ABSTRACTION and SINGLE RESPONSIBILITY.
 */
public class AuthorizationService {

    /**
     * Check if a user has permission to perform an action.
     *
     * @param user The logged-in user
     * @param permission The requested permission
     * @return true if allowed, false otherwise
     */
    public boolean hasPermission(User user, Permission permission) {

        if (user == null) {
            return false;
        }

        String role = user.getRole();

        switch (role) {
            case "ADMIN":
                return adminPermissions(permission);

            case "INSTRUCTOR":
                return instructorPermissions(permission);

            case "STUDENT":
                return studentPermissions(permission);

            default:
                return false;
        }
    }

    // ===============================
    // ADMIN permissions
    // ===============================
    private boolean adminPermissions(Permission permission) {
        // Admin has full access
        return true;
    }

    // ===============================
    // INSTRUCTOR permissions
    // ===============================
    private boolean instructorPermissions(Permission permission) {
        switch (permission) {
            case ASSIGN_GRADE:
            case VIEW_COURSES:
            case VIEW_ENROLLMENTS:
            case GENERATE_REPORT:
                return true;
            default:
                return false;
        }
    }

    // ===============================
    // STUDENT permissions
    // ===============================
    private boolean studentPermissions(Permission permission) {
        switch (permission) {
            case ENROLL_STUDENT:
            case VIEW_COURSES:
            case VIEW_TRANSCRIPT:
                return true;
            default:
                return false;
        }
    }
}
