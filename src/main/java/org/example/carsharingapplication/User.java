package org.example.carsharingapplication;

//Getter and Setter Annotations:
//import lombok.Getter;
//import lombok.Setter;

/**
 *
 * This class is the User class to be used by the Car Sharing Application.
 *
 * @version 1.0
 * @since 13-03-2025
 * @author thedizzle69
 *
 */

class User {
    private String username;
    private String password;
    private String role; // Assuming role is either "fleet-manager" or customer

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

