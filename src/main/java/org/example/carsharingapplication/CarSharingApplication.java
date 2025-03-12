package org.example.carsharingapplication;

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

class User {
    // Getters and setters
    private String username;
    private String password;
    private String role; // Assuming role is either "fleet-manager" or customer

}

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
