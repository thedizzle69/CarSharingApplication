package org.example.carsharingapplication;

//Springboot imports
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//Java utilities
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** This class is the main class to be used by the Car Sharing Application.
 *
 * @version 1.0
 * @since 13-03-2025
 * @author thedizzle69
 *
 *
 */

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class CarSharingApplication {

    private Map<String, User> users = new HashMap<>(); // In-memory storage for users
    private Map<Long, Vehicle> vehicles = new HashMap<>(); // In-memory storage for vehicles

    public static void main(String[] args) {
        SpringApplication.run(CarSharingApplication.class, args);
    }



}





