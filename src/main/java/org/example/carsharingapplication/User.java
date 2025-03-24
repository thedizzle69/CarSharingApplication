package org.example.carsharingapplication;


/**
 * This class represents a User in the Car Sharing Application.
 *
 * @version 3.0
 * @since 13-03-2025
 * @author Jai SINGH
 */

public class User {
    private String username;
    private String password;
    private String role; // "fleet-manager" or "driver"
    private String firstName;
    private String lastName;
    private Integer age;
    private String licenseNo;
    private String creditCardNo;

    public User(String username, String password, String role, String firstName, String lastName, Integer age, String licenseNo, String creditCardNo) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.licenseNo = licenseNo;
        this.creditCardNo = creditCardNo;
    }

    //Constructor

    public User() {
    }

    // Getters and Setters

    //Getters

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getAge() {
        return age;
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public String getCreditCardNo() {
        return creditCardNo;
    }

    //Setters

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    public void setCreditCardNo(String creditCardNo) {
        this.creditCardNo = creditCardNo;
    }

}
