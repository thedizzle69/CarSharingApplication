package org.example.carsharingapplication;

import lombok.*;

/**
 * This class represents a Vehicle in the Car Sharing Application.
 *
 * @version 2.0
 * @since 26-03-2025
 * @author Jai SINGH
 */

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Vehicle {
    private long id;
    private String name;
    private String description;
    private double latitude;
    private double longitude;
    private String state;
    private String currentDriver;
    private String vehicleToken;

    // current timestamp
    private long lastUpdated = System.currentTimeMillis();
    // distance travelled
    private double StartKM;
    private double EndKM;
    private double distanceTravelled;

}
