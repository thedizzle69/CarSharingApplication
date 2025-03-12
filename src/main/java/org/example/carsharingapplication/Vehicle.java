package org.example.carsharingapplication;

//Getter and Setter Annotations:
//import lombok.Getter;
//import lombok.Setter;

/** This class is the Vehicle class to be used by the Car Sharing Application.
 *
 * @version 1.0
 * @since 13-03-2025
 * @author thedizzle69
 *
 */

class Vehicle {
    private long id;
    private String name;
    private String description;
    private double latitude;
    private double longitude;
    private String state;
    private String currentDriver;
    private String vehicleToken;

    // Getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCurrentDriver() {
        return currentDriver;
    }

    public void setCurrentDriver(String currentDriver) {
        this.currentDriver = currentDriver;
    }

    public String getVehicleToken() {
        return vehicleToken;
    }

    public void setVehicleToken(String vehicleToken) {
        this.vehicleToken = vehicleToken;
    }
}
