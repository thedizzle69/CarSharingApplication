package org.example.carsharingapplication;

//Getter and Setter Annotations:
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Setter
@Getter
@SpringBootApplication
@RestController
@RequestMapping("/api")

/**
 * This class is the main entry point for the Car Sharing Application.
 *
 * @version 1.0
 * @since 13-03-2025
 * @author thedizzle69
 *
 */



/** This class is the User class to be used by the Car Sharing Application.
 *
 * @version 1.0
 * @since 13-03-2025
 * @author thedizzle69
 *
 * @param username The username of the user
 * @param password The password of the user
 * @param role The role of the user
 *
 */

class User {

    private String username;
    private String password;
    private String role; // Possible roles: "fleet-manager", "driver"

}

/** This class is the Vehicle class to be used by the Car Sharing Application.
 *
 * @version 1.0
 * @since 13-03-2025
 * @author thedizzle69
 *
 * @param id The id of the vehicle
 * @param name The name of the vehicle
 * @param description The description of the vehicle
 * @param latitude The latitude of the vehicle
 * @param longitude The longitude of the vehicle
 * @param state The state of the vehicle
 * @param currentDriver The current driver of the vehicle
 * @param vehicleToken The token of the vehicle
 *
 */

class Vehicle {
    private long id;
    private String name;
    private String description;
    private double latitude;
    private double longitude;
    private String state;
    private String currentDriver;
    private String vehicleToken;

}
