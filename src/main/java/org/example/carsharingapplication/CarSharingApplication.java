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
    public ResponseEntity<String> loginUser(@RequestBody User loginUser) {
        User user = users.get(loginUser.getUsername());
        if (user != null && user.getPassword().equals(loginUser.getPassword())) {
            // Simulating a token by returning a simple generated string
            String token = "token_" + loginUser.getUsername();
            user.setAuthToken(token); // Store the token in-memory
            return new ResponseEntity<>(token, HttpStatus.OK);
        }
        return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/users/logout")
    public ResponseEntity<String> logoutUser(@RequestHeader("Authorization") String authToken) {
        // Perform logout logic here
        return new ResponseEntity<>("Logged out successfully", HttpStatus.OK);
    }

    // Fleet Manager /users
    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers(@RequestHeader("Authorization") String authToken) {
        // Validate token
        for (User user : users.values()) {
            if (user.getAuthToken() != null && user.getAuthToken().equals(authToken)) {
                if (user.getRole().equals("fleet-manager")) {
                    return new ResponseEntity<>(users.values(), HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("Forbidden", HttpStatus.FORBIDDEN);
                }
            }
        }
        return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
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
    public ResponseEntity<String> deleteVehicle(@PathVariable("id") long id, @RequestHeader("Authorization") String authToken) {
        // Check if the user is authenticated and a fleet manager
        for (User user : users.values()) {
            if (user.getAuthToken() != null && user.getAuthToken().equals(authToken) && user.getRole().equals("fleet-manager")) {
                if (vehicles.containsKey(id)) {
                    vehicles.remove(id);
                    return new ResponseEntity<>("Vehicle deleted successfully", HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("Vehicle not found", HttpStatus.NOT_FOUND);
                }
            }
        }
        return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
    }


}





