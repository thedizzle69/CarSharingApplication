package org.example.carsharingapplication;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class CarSharingClient {

    private static final String BASE_URL = "http://localhost:8080/api";
    private static final RestTemplate restTemplate = new RestTemplate();
    private static String authToken = null; // Stores authentication token after login

    public static void main(String[] args) {
        registerUser();
        loginUser();
        getAllVehicles();
        getAllUsers();
    }

    // 🟢 Register a new user
    private static void registerUser() {
        String url = BASE_URL + "/users/register";
        Map<String, Object> requestBody = Map.of(
                "username", "BigBoss1",
                "password", "BossPW123",
                "role", "fleet-manager",
                "firstName", "Jai",
                "lastName", "Singh",
                "age", 33,
                "licenseNo", "010192",
                "creditCardNo", "1234567890"
        );

        ResponseEntity<String> response = restTemplate.postForEntity(url, requestBody, String.class);
        System.out.println("Register User: " + response.getStatusCode() + " - " + response.getBody());
    }

    // Login and store token
    private static void loginUser() {
        String url = BASE_URL + "/users/login";
        Map<String, String> requestBody = Map.of(
                "username", "BigBoss1",
                "password", "BossPW123"
        );

        ResponseEntity<Map> response = restTemplate.postForEntity(url, requestBody, Map.class);
        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            authToken = "Bearer " + response.getBody().get("token"); // Store token
            System.out.println("Login Successful: " + authToken);
        } else {
            System.out.println("Login Failed!");
        }
    }

    // Get all vehicles
    private static void getAllVehicles() {
        String url = BASE_URL + "/vehicles";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        System.out.println("Vehicles: " + response.getStatusCode() + " - " + response.getBody());
    }

    // Get all users (Only Fleet Managers can)
    private static void getAllUsers() {
        String url = BASE_URL + "/users";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        System.out.println("Users: " + response.getStatusCode() + " - " + response.getBody());
    }
}

