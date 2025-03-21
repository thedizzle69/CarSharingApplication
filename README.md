# CarSharingApplication
CarSharingApplication for the course Software Architecture and Middleware on the UAS Technikum Vienna. By Jai SINGH (ic22b037)

## Versions

Program worked on since 13-03-2025
Current Version 2.0 in production since 21-03-2025

## Testing with `carsharing-tests.http`

A predefined `carsharing-tests.http` file is available for API testing. Load it into Postman or an HTTP client to automate requests.

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
- **Postman/http files** (for API testing)

## Installation & Setup
1. **Clone the repository:**
   ```sh
   git clone https://github.com/thedizzle69/CarSharingApplication
   ```
2. **Build and run the application:**
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

## Authentication
- **Login** returns a token in this format: `Bearer token_username`.
- Include the token in the `Authorization` header for requests that require authentication:
  ```sh
  Authorization: Bearer token_username
  ```


