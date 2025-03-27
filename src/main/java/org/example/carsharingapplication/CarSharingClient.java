package org.example.carsharingapplication;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/*
 * This class is the main class to be used by the Car Sharing Client.
 * It sends HTTP requests to the Car Sharing Application.
 * @version 2.0
 * @since 27-03-2025
 * @author Jai SINGH
 */

public class CarSharingClient {

    private static final String BASE_URL = "http://localhost:8080/api";
    private static final RestTemplate restTemplate = new RestTemplate();
    private static String authToken = null; // Stores authentication token after login

    public static void main(String[] args) {
        System.out.println("--- Fleet Manager Workflow ---");
        testFleetManagerWorkflow();

        System.out.println("--- Driver Workflow ---");
        testDriverWorkflow();
    }

    private static void testFleetManagerWorkflow() {
        registerUser();
        loginUser();
        getAllUsers();
        registerVehicle();
        getAllVehicles();
        getVehicleById(1);
        updateVehicle(1);
        deleteVehicle(1);
        getAllVehicles();
        logoutUser();
    }

    private static void testDriverWorkflow() {
        registerDriver();
        loginDriver();
        // attemptUnauthorizedActions(); // Uncomment to test unauthorized actions
        logoutUser();
    }

    // Register a new user (Fleet Manager)
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

        sendPostRequest(url, requestBody);
    }

    // Login as fleet manager and store token
    private static void loginUser() {
        String url = BASE_URL + "/users/login";
        Map<String, String> requestBody = Map.of(
                "username", "BigBoss1",
                "password", "BossPW123"
        );

        ResponseEntity<String> response = sendPostRequest(url, requestBody);
        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            authToken = "Bearer " + extractToken(response.getBody());
            System.out.println("Login Successful: " + authToken);
        }
    }

    // Register a new vehicle
    private static void registerVehicle() {
        String url = BASE_URL + "/vehicles";
        Map<String, Object> requestBody = Map.ofEntries(
                Map.entry ("id", 1),
                Map.entry ("name", "3er BMW"),
                Map.entry ("description", "Bavarian car"),
                Map.entry ("latitude", 42.0169),
                Map.entry ("longitude", 16.3738),
                Map.entry ("state", "free"),
                Map.entry ("currentDriver", "none"),
                Map.entry ("vehicleToken", "BMW123"),
                Map.entry ("lastUpdated", 1620000000000L),
                Map.entry ("StartKM", 0),
                Map.entry ("EndKM", 0),
                Map.entry ("distanceTravelled", 0)
        );

        sendPostRequestWithAuth(url, requestBody);
    }

    // Get all vehicles
    private static void getAllVehicles() {
        sendGetRequest(BASE_URL + "/vehicles");
    }

    // Get all users (Only Fleet Managers can)
    private static void getAllUsers() {
        sendGetRequest(BASE_URL + "/users");
    }

    // Get specific vehicle by ID
    private static void getVehicleById(int vehicleId) {
        sendGetRequest(BASE_URL + "/vehicles/" + vehicleId);
    }

    // Update a vehicle
    private static void updateVehicle(int vehicleId) {
        String url = BASE_URL + "/vehicles/" + vehicleId;
        Map<String, Object> requestBody = Map.ofEntries(
                Map.entry("id", vehicleId),
                Map.entry("name", "Updated 3er BMW"),
                Map.entry("description", "Updated bavarian car."),
                Map.entry("latitude", 42.0169),
                Map.entry("longitude", 16.3738),
                Map.entry("state", "occupied"),
                Map.entry("currentDriver", "BMWDriver1"),
                Map.entry("vehicleToken", "BMW456"),
                Map.entry("lastUpdated", 1620000000000L),
                Map.entry("StartKM", 0),
                Map.entry("EndKM", 0),
                Map.entry("distanceTravelled", 0)
        );

        sendPutRequestWithAuth(url, requestBody);
    }

    // Delete a vehicle
    private static void deleteVehicle(int vehicleId) {
        sendDeleteRequest(BASE_URL + "/vehicles/" + vehicleId);
    }

    // Register a driver
    private static void registerDriver() {
        String url = BASE_URL + "/users/register";
        Map<String, Object> requestBody = Map.of(
                "username", "BMWDriver1",
                "password", "NoTurnSignal123",
                "role", "driver",
                "firstName", "Johra-Markus",
                "lastName", "Singh",
                "age", 33,
                "licenseNo", "010100",
                "creditCardNo", "0987654322"
        );

        sendPostRequest(url, requestBody);
    }

    // Login as driver
    private static void loginDriver() {
        String url = BASE_URL + "/users/login";
        Map<String, String> requestBody = Map.of(
                "username", "BMWDriver1",
                "password", "NoTurnSignal123"
        );

        ResponseEntity<String> response = sendPostRequest(url, requestBody);
        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            authToken = "Bearer " + extractToken(response.getBody());
            System.out.println("Driver Login Successful: " + authToken);
        }
    }

    // Attempt unauthorized actions as a driver
    private static void attemptUnauthorizedActions() {
        sendGetRequest(BASE_URL + "/vehicles"); // Expected: Forbidden
        sendGetRequest(BASE_URL + "/users");    // Expected: Forbidden
    }

    // Logout user
    private static void logoutUser() {
        sendPostRequestWithAuth(BASE_URL + "/users/logout", Map.of());
    }

    // Generic POST request
    private static ResponseEntity<String> sendPostRequest(String url, Map<String, ?> requestBody) {
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, requestBody, String.class);
            System.out.println("POST " + url + " -> " + response.getStatusCode() + " | " + response.getBody());
            return response;
        } catch (Exception e) {
            System.err.println("Error during POST request to " + url + ": " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // POST request with Authorization
    private static void sendPostRequestWithAuth(String url, Map<String, ?> requestBody) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, ?>> entity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        System.out.println("POST (Auth) " + url + " -> " + response.getStatusCode() + " | " + response.getBody());
    }

    // Generic GET request
    private static void sendGetRequest(String url) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        System.out.println("GET " + url + " -> " + response.getStatusCode() + " | " + response.getBody());
    }

    // PUT request with Authorization
    private static void sendPutRequestWithAuth(String url, Map<String, ?> requestBody) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, ?>> entity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);
        System.out.println("PUT " + url + " -> " + response.getStatusCode() + " | " + response.getBody());
    }

    // DELETE request with Authorization
    private static void sendDeleteRequest(String url) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, entity, String.class);
        System.out.println("DELETE " + url + " -> " + response.getStatusCode() + " | " + response.getBody());
    }

    private static String extractToken(String responseBody) {
        return responseBody.replace("{\"token\":\"", "").replace("\"}", "");
    }
}
