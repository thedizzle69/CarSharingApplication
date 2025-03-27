package org.example.carsharingapplication;

import org.springframework.http.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

public class CarSharingClient {

    private static final String BASE_URL = "http://localhost:8080/api";
    private static final RestTemplate restTemplate = new RestTemplate();
    private static String fleetManagerToken = null;
    private static String driverToken = null;

    public static void main(String[] args) {
        testFleetManagerWorkflow();
        testDriverWorkflow();
    }

    private static void testFleetManagerWorkflow() {
        System.out.println("--- Fleet Manager Workflow ---");
        registerUser("BigBoss1", "BossPW123", "fleet-manager");
        registerUser("BigBoss1", "BossPW123", "fleet-manager"); // Expected 409 Conflict
        loginUser("BigBoss1", "BossPW123", true);
        getAllVehicles();
        getAllUsers();
        registerVehicle(1);
        getVehicleById(1);
        updateVehicle(1);
        deleteVehicle(1);
        logout(fleetManagerToken);
    }

    private static void testDriverWorkflow() {
        System.out.println("--- Driver Workflow ---");
        registerUser("BMWDriver1", "NoTurnSignal123", "driver");
        loginUser("BMWDriver1", "NoTurnSignal123", false);
        getAllVehiclesForbidden(driverToken);
        getAllUsersForbidden(driverToken);
        logout(driverToken);
    }

    private static void registerUser(String username, String password, String role) {
        String url = BASE_URL + "/users/register";
        Map<String, Object> requestBody = Map.of(
                "username", username,
                "password", password,
                "role", role,
                "firstName", "John",
                "lastName", "Doe",
                "age", 30,
                "licenseNo", "123456",
                "creditCardNo", "1234123412341234"
        );
        sendPostRequest(url, requestBody);
    }

    private static void loginUser(String username, String password, boolean isFleetManager) {
        String url = BASE_URL + "/users/login";
        Map<String, String> requestBody = Map.of("username", username, "password", password);
        ResponseEntity<Map> response = sendPostRequest(url, requestBody);
        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            String token = "Bearer " + response.getBody().get("token");
            if (isFleetManager) fleetManagerToken = token;
            else driverToken = token;
            System.out.println(username + " logged in successfully.");
        }
    }

    private static void getAllVehicles() {
        sendGetRequest(BASE_URL + "/vehicles", fleetManagerToken);
    }

    private static void getAllUsers() {
        sendGetRequest(BASE_URL + "/users", fleetManagerToken);
    }

    private static void registerVehicle(int vehicleId) {
        String url = BASE_URL + "/vehicles";
        Map<String, Object> requestBody = Map.of(
                "id", vehicleId,
                "name", "3er BMW",
                "description", "Bavarian car",
                "latitude", 42.0169,
                "longitude", 16.3738,
                "state", "free",
                "currentDriver", null,
                "vehicleToken", "BMW123"
        );
        sendPostRequest(url, requestBody, fleetManagerToken);
    }

    private static void getVehicleById(int vehicleId) {
        sendGetRequest(BASE_URL + "/vehicles/" + vehicleId, fleetManagerToken);
    }

    private static void updateVehicle(int vehicleId) {
        String url = BASE_URL + "/vehicles/" + vehicleId;
        Map<String, Object> requestBody = Map.of(
                "id", vehicleId,
                "name", "Updated BMW",
                "description", "Updated Bavarian car",
                "latitude", 42.0169,
                "longitude", 16.3738,
                "state", "occupied",
                "currentDriver", "BMWDriver1",
                "vehicleToken", "BMW456"
        );
        sendPutRequest(url, requestBody, fleetManagerToken);
    }

    private static void deleteVehicle(int vehicleId) {
        sendDeleteRequest(BASE_URL + "/vehicles/" + vehicleId, fleetManagerToken);
    }

    private static void getAllVehiclesForbidden(String token) {
        sendGetRequestForbidden(BASE_URL + "/vehicles", token);
    }

    private static void getAllUsersForbidden(String token) {
        sendGetRequestForbidden(BASE_URL + "/users", token);
    }

    private static void logout(String token) {
        sendPostRequest(BASE_URL + "/users/logout", Map.of(), token);
    }

    private static ResponseEntity<Map> sendPostRequest(String url, Map<String, ?> body) {
        return sendPostRequest(url, body, null);
    }

    private static ResponseEntity<Map> sendPostRequest(String url, Map<String, ?> body, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        if (token != null) headers.set("Authorization", token);
        HttpEntity<Map<String, ?>> entity = new HttpEntity<>(body, headers);
        try {
            return restTemplate.exchange(url, HttpMethod.POST, entity, Map.class);
        } catch (HttpStatusCodeException e) {
            System.out.println("POST " + url + " -> " + e.getStatusCode());
            return new ResponseEntity<>(e.getStatusCode());
        }
    }

    private static void sendGetRequest(String url, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            System.out.println("GET " + url + " -> " + response.getStatusCode());
        } catch (HttpStatusCodeException e) {
            System.out.println("GET " + url + " -> " + e.getStatusCode());
        }
    }

    private static void sendGetRequestForbidden(String url, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        try {
            restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            System.out.println("ERROR: " + url + " should have returned 403");
        } catch (HttpStatusCodeException e) {
            System.out.println("GET " + url + " -> " + e.getStatusCode());
        }
    }

    private static void sendPutRequest(String url, Map<String, ?> body, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<Map<String, ?>> entity = new HttpEntity<>(body, headers);
        restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);
        System.out.println("PUT " + url + " -> 200 OK");
    }

    private static void sendDeleteRequest(String url, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        restTemplate.exchange(url, HttpMethod.DELETE, entity, String.class);
        System.out.println("DELETE " + url + " -> 200 OK");
    }
}