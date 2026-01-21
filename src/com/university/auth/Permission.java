package com.university.auth;

/**
 * Enum representing all possible permissions in the University Management System.
 * Demonstrates ABSTRACTION and type safety.
 */
public enum Permission {
    // Student permissions
    ENROLL_STUDENT,
    VIEW_TRANSCRIPT,
    VIEW_COURSES,

    // Instructor permissions
    ASSIGN_GRADE,
    VIEW_ENROLLMENTS,
    GENERATE_REPORT,

    // Admin permissions
    MANAGE_USERS,
    MANAGE_COURSES,
    SEND_ANNOUNCEMENTS
}
