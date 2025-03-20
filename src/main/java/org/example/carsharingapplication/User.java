package org.example.carsharingapplication;

//Getter and Setter Annotations:

import lombok.Getter;
import lombok.Setter;

/**
 *
 * This class is the User class to be used by the Car Sharing Application.
 *
 * @version 1.0
 * @since 13-03-2025
 * @author Jai SINGH
 *
 */

@Setter
@Getter
class User {
    private String username;
    private String password;
    private String role; // "fleet-manager" or "customer"
    private String authToken; // Store token for authentication
}

