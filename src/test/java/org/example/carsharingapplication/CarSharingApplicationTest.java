package org.example.carsharingapplication;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CarSharingApplicationTest {
    // This is a placeholder for the test
}

class UserTest {

    @Test
    void testUserGettersAndSetters() {
        User user = new User();

        user.setUsername("testUser");
        user.setPassword("Password123");
        user.setRole("fleet-manager");

        assertEquals("testUser", user.getUsername());
        assertEquals("Password123", user.getPassword());
        assertEquals("fleet-manager", user.getRole());
    }
}

