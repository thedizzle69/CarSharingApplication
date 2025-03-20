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

    // Fleet Manager /users
    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers(@RequestHeader("Authorization") String authToken) {
        // Simulated role check for demonstration
        if (users.get(authToken).getRole().equals("fleet-manager")) {
            return new ResponseEntity<>(users.values(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Forbidden", HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<String> updateUser(@PathVariable("id") String userId, @RequestBody User user) {
        // Update logic here
        users.put(userId, user);
        return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
    }

    // Vehicle Management
    @PostMapping("/vehicles")
    public ResponseEntity<String> registerVehicle(@RequestBody Vehicle vehicle) {
        vehicles.put(vehicle.getId(), vehicle);
        return new ResponseEntity<>("Vehicle registered successfully", HttpStatus.CREATED);
    }

    @GetMapping("/vehicles")
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        return new ResponseEntity<>(List.copyOf(vehicles.values()), HttpStatus.OK);
    }

    @GetMapping("/vehicles/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable("id") long id) {
        Vehicle vehicle = vehicles.get(id);
        if (vehicle != null) {
            return new ResponseEntity<>(vehicle, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/vehicles/{id}")
    public ResponseEntity<String> updateVehicle(@PathVariable("id") long id, @RequestBody Vehicle vehicle) {
        // Update logic here
        vehicles.put(id, vehicle);
        return new ResponseEntity<>("Vehicle updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/vehicles/{id}")
    public ResponseEntity<String> deleteVehicle(@PathVariable("id") long id) {
        // Delete logic here
        vehicles.remove(id);
        return new ResponseEntity<>("Vehicle deleted successfully", HttpStatus.OK);
    }

}