package com.flightmanagement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BusinessFlightTest {

    @Test
    void testBusinessFlightWithRegularPassenger() {
        Flight flight = new BusinessFlight("3");
        Passenger mike = new Passenger("Mike", false);

        assertFalse(flight.addPassenger(mike));
        assertEquals(0, flight.getPassengers().size());
        assertFalse(flight.removePassenger(mike));
    }

    @Test
    void testBusinessFlightWithVipPassenger() {
        Flight flight = new BusinessFlight("4");
        Passenger james = new Passenger("James", true);

        assertTrue(flight.addPassenger(james));
        assertEquals(1, flight.getPassengers().size());
        assertFalse(flight.removePassenger(james));
    }
}