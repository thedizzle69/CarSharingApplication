package org.example.carsharingapplication;


import lombok.*;

/**
 * This class represents a User in the Car Sharing Application.
 *
 * @version 3.0
 * @since 13-03-2025
 * @author Jai SINGH
 */

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor

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
