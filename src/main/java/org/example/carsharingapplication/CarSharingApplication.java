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

//Lombok imports
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Data;
import lombok.AccessLevel;

/** This class is the main class to be used by the Car Sharing Application.
 *
 * @version 1.0
 * @since 13-03-2025
 * @author Jai SINGH
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

    // User Management
    @PostMapping("/users/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        users.put(user.getUsername(), user);
        return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
    }

    @PostMapping("/users/login")
    public ResponseEntity<String> loginUser(@RequestHeader("Authorization") String auth) {
        // Perform basic authentication check here
        // Simulated logic for demonstration
        if (auth.equals("Basic username:password")) {
            return new ResponseEntity<>("Login successful", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/users/logout")
    public ResponseEntity<String> logoutUser(@RequestHeader("Authorization") String authToken) {
        // Perform logout logic here
        return new ResponseEntity<>("Logged out successfully", HttpStatus.OK);
    }



}





