package org.example.carsharingapplication;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testUser() {
        User user = new User();

        user.setUsername("testUser");
        user.setPassword("Password123");
        user.setRole("fleet-manager");

        assertEquals("testUser", user.getUsername());
        assertEquals("Password123", user.getPassword());
        assertEquals("fleet-manager", user.getRole());
    }
}
