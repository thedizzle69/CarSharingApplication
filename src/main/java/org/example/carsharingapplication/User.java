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
 * @author thedizzle69
 *
 */

@Getter
@Setter
class User {

    private String username;
    private String password;
    private String role; // Possible roles: "fleet-manager", "driver"

}
