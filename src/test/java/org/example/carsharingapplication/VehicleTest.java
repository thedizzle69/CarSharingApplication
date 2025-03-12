package org.example.carsharingapplication;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VehicleTest {

    @Test
    void testVehicle() {
        Vehicle vehicle = new Vehicle();

        vehicle.setId(1);
        vehicle.setName("BMW M3");
        vehicle.setDescription("Ich komm' im 3er BMW");
        vehicle.setLatitude(69.6969);
        vehicle.setLongitude(-100.4200);
        vehicle.setState("active");
        vehicle.setCurrentDriver("Max Mustermann");
        vehicle.setVehicleToken("abcdef");

        assertEquals(1, vehicle.getId());
        assertEquals("BMW M3", vehicle.getName());
        assertEquals("Ich komm' im 3er BMW", vehicle.getDescription());
        assertEquals(69.6969, vehicle.getLatitude());
        assertEquals(-100.4200, vehicle.getLongitude());
        assertEquals("active", vehicle.getState());
        assertEquals("Max Mustermann", vehicle.getCurrentDriver());
        assertEquals("abcdef", vehicle.getVehicleToken());
    }
}
