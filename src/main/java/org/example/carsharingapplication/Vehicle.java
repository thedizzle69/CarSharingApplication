package org.example.carsharingapplication;

//Getter and Setter Annotations:

import lombok.Getter;
import lombok.Setter;

/** This class is the Vehicle class to be used by the Car Sharing Application.
 *
 * @version 1.0
 * @since 13-03-2025
 * @author Jai SINGH
 *
 */

@Setter
@Getter
class Vehicle {
    // Getters and setters
    private long id;
    private String name;
    private String description;
    private double latitude;
    private double longitude;
    private String state;
    private String currentDriver;
    private String vehicleToken;

}
