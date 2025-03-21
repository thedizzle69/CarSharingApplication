package org.example.carsharingapplication;

/**
 * This class represents a Vehicle in the Car Sharing Application.
 *
 * @version 2.0
 * @since 13-03-2025
 * @author Jai SINGH
 */
public class Vehicle {
    private long id;
    private String name;
    private String description;
    private double latitude;
    private double longitude;
    private String state;
    private String currentDriver;
    private String vehicleToken;

    // Constructor
    public Vehicle() {}

    public Vehicle(long id, String name, String description, double latitude, double longitude,
                   String state, String currentDriver, String vehicleToken) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.state = state;
        this.currentDriver = currentDriver;
        this.vehicleToken = vehicleToken;
    }

    // Getters
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getState() {
        return state;
    }

    public String getCurrentDriver() {
        return currentDriver;
    }

    public String getVehicleToken() {
        return vehicleToken;
    }

    // Setters
    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCurrentDriver(String currentDriver) {
        this.currentDriver = currentDriver;
    }

    public void setVehicleToken(String vehicleToken) {
        this.vehicleToken = vehicleToken;
    }
}
