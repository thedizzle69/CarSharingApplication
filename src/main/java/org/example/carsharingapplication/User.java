package org.example.carsharingapplication;

/**
 * This class represents a User in the Car Sharing Application.
 *
 * @version 1.0
 * @since 13-03-2025
 * @author Jai SINGH
 */
public class User {
    private String username;
    private String password;
    private String role; // "fleet-manager" or "driver"

    // Constructor
    public User() {}

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    // Setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
