package org.example.carsharingapplication;

//Springboot imports
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

//Java utils
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** This class is the main class to be used by the Car Sharing Application.
 *
 * @version 3.0
 * @since 13-03-2025
 * @author Jai SINGH
 *
 *
 */

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class CarSharingApplication {

    private final Map<String, User> users = new HashMap<>(); // In-memory storage for users
    private final Map<Long, Vehicle> vehicles = new HashMap<>(); // In-memory storage for vehicles
    private final Map<String, User> authTokens = new HashMap<>(); // Stores auth tokens mapped to users

    public static void main(String[] args) {
        SpringApplication.run(CarSharingApplication.class, args);
    }

    // Authentication method to avoid duplicate later with Token Bearer

    private void authenticate(String authHeader, String requiredRole) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized: Missing or invalid token");
        }

        String authToken = authHeader.substring(7); // Extract actual token after "Bearer "

        User user = authTokens.get(authToken);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized: Invalid token");
        }

        if (requiredRole != null && !user.getRole().equals(requiredRole)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Forbidden: Insufficient permissions");
        }
    }

    // User Management
    @PostMapping("/users/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        users.put(user.getUsername(), user);
        return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
    }

    @PostMapping("/users/login")
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        User user = users.get(username);
        if (user == null || !user.getPassword().equals(password)) {
            return new ResponseEntity<>(Map.of("error", "Invalid credentials"), HttpStatus.BAD_REQUEST);
        }

        // Generate a simple token
        String token = "token_" + username;

        // Store token-to-user mapping
        authTokens.put(token, user);

        return new ResponseEntity<>(Map.of("token", token), HttpStatus.OK);
    }

    @PostMapping("/users/logout")
    public ResponseEntity<String> logoutUser() {
        return new ResponseEntity<>("Logged out successfully", HttpStatus.OK);
    }

    // Fleet Manager /users
    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers(@RequestHeader("Authorization") String authToken) {
        authenticate(authToken, "fleet-manager");  // 🔹 Calls helper method
        return new ResponseEntity<>(users.values(), HttpStatus.OK);
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
    public ResponseEntity<List<Vehicle>> getAllVehicles(@RequestHeader("Authorization") String authToken) {
        authenticate(authToken, "fleet-manager");  // Using helper method for authentication and role check
        return new ResponseEntity<>(List.copyOf(vehicles.values()), HttpStatus.OK);
    }

    @GetMapping("/vehicles/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable("id") long id,
                                                  @RequestHeader("Authorization") String authToken) {
        authenticate(authToken, "fleet-manager");
        Vehicle vehicle = vehicles.get(id);
        if (vehicle != null) {
            return new ResponseEntity<>(vehicle, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/vehicles/{id}")
    public ResponseEntity<String> updateVehicle(@RequestHeader("Authorization") String authToken,
                                                @PathVariable("id") long id,
                                                @RequestBody Vehicle vehicle) {
        authenticate(authToken, "fleet-manager");  // Using helper method for authentication and role check

        vehicles.put(id, vehicle);
        return new ResponseEntity<>("Vehicle updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/vehicles/{id}")
    public ResponseEntity<String> deleteVehicle(@RequestHeader("Authorization") String authToken,
                                                @PathVariable("id") long id) {
        authenticate(authToken, "fleet-manager");  // Using helper method for authentication and role check

        vehicles.remove(id);
        return new ResponseEntity<>("Vehicle deleted successfully", HttpStatus.OK);
    }

}