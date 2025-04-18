# CarSharingApplication
CarSharingApplication for the course Software Architecture and Middleware on the UAS Technikum Vienna. By Jai SINGH (ic22b037)

## Versions

Program worked on since 13-03-2025
Current Version 5.0 in production since 27-03-2025

## Testing with `CarSharingClient.java`

A predefined `CarSharingClient.java` file is available for API testing.
Run the `/src/main/java/org.example.carsharingapplication/CarSharingApplication.java` file to start the application.
Run the `/src/main/java/org.example.carsharingapplication/CarSharingClient.java` file to test the API.
The Forbidden Action Test for the driver is commented out in the `CarSharingClient.java` file. Uncomment it to test the forbidden action.
In the http test it is activated and will return 403 Forbidden.

## Testing with SwaggerUI

Via the link 'http://localhost:8080/swagger-ui/index.html' you can also visualize the API and test it. 

## Testing with `carsharing-tests.http`

A predefined `carsharing-tests.http` file is available for API testing.
Run the `/src/main/java/org.example.carsharingapplication/CarSharingApplication.java` file to start the application.
Load the `carsharing-tests.http` it into an HTTP client to automate requests.

Following tests are implemented in the `carsharing-tests.http` file in this order:

- Register a new user (Fleet Manager) (201 Created)
- Register with same username (409 Conflict)
- Login as non-existent user (400 Bad Request)
- Login with existing user but wrong password (400 Bad Request)
- Login as fleet manager (Retrieve Token): (200 OK)
- Retrieve all vehicles as Fleet-Manager (200 OK)
- Fleet manager retrieves all users (200 OK)
- Fleet manager registers a new vehicle (201 Created)
- Retrieve a specific vehicle by ID (200 OK)
- Fleet manager updates a vehicle (200 OK)
- Wrong Login Attempt (400 Bad Request)
- Register a normal user (Driver) (201 Created)
- Login as driver (Retrieve Token) (200 OK)
- Retrieve all vehicles (203 Forbidden)
- Driver trying to retrieve all users (Should fail) (403 Forbidden)
- Driver trying to retrieve a specific vehicle by ID (403 Forbidden)
- Driver trying to update a vehicle (403 Forbidden)
- Fleet manager wants to register an already existing vehicle (409 Conflict)
- Export vehicles to csv file (Fleet manager) (200 OK)
- Driver trying to delete a vehicle (403 Forbidden)
- Fleet manager deletes a vehicle (200 OK)
- Logging out as driver (200 OK)
- Logging out as fleet manager (200 OK)


## Overview
The Car Sharing Application is a Spring Boot-based REST API that facilitates user registration, authentication, and vehicle management for a car-sharing service. The application supports two user roles:

- **Fleet Manager**: Can manage vehicles and view all users.
- **Driver**: Can view available vehicles but cannot manage them.

## Features
- **User Management:** Register, login, and logout users.
- **Authentication & Authorization:** Token-based authentication with role-based access control.
- **Vehicle Management:** Restricted to fleet managers.
- **Security:** Basic authentication with token-based access control.

## Technologies Used
- **Spring Boot** (Spring Web)
- **Java 21**
- **Maven**
- **http files** (for API testing)

## Installation & Setup

1.**Build and run the application:**
   ```sh
   mvn spring-boot:run
   ```
3. **API Endpoints:** (Runs on `http://localhost:8080/api`)

## API Endpoints

### User Management
| Method | Endpoint | Description |
|--------|---------|-------------|
| POST | `/users/register` | Register a new user |
| POST | `/users/login` | Login user and receive auth token |
| POST | `/users/logout` | Logout user |
| GET | `/users` | Retrieve all users (Fleet Manager only) |

### Vehicle Management
| Method | Endpoint | Description |
|--------|---------|-------------|
| POST | `/vehicles` | Register a new vehicle (Fleet Manager only) |
| GET | `/vehicles` | Retrieve all vehicles |
| GET | `/vehicles/{id}` | Retrieve a vehicle by ID |
| PUT | `/vehicles/{id}` | Update a vehicle (Fleet Manager only) |
| DELETE | `/vehicles/{id}` | Delete a vehicle (Fleet Manager only) |
| GET    | `/vehicles/export` | Get a vehicles.csv back in http request.    |

## Authentication
- **Login** returns a token in this format: `Bearer token_username`.
- Include the token in the `Authorization` header for requests that require authentication:
  ```sh
  Authorization: Bearer token_username
  ```


## OpenAI

AI tries a lot. Too much. It's like a child. It's like a child who's trying to learn how to walk. (Last part is also Copilot dissing itself).

No seriously. It is ok to let you help and you don't have to write too much manually. But it is a tool. Not more. 

