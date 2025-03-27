package org.example.carsharingapplication;

import lombok.*;

/**
 * This class represents a Vehicle in the Car Sharing Application.
 *
 * @version 3.0
 * @since 26-03-2025
 * @author Jai SINGH
 */

@Data
@AllArgsConstructor
@NoArgsConstructor

public class User {

    private String username;
    private String password;
    private String role; // "fleet-manager" or "driver"
    private String firstName;
    private String lastName;
    private Integer age;
    private String licenseNo;
    private String creditCardNo;

}


