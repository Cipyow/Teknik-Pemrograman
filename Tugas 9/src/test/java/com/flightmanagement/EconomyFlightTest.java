package com.flightmanagement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EconomyFlightTest {

    @Test
    void testEconomyFlightWithRegularPassenger() {
        Flight flight = new EconomyFlight("1");
        Passenger mike = new Passenger("Mike", false);

        assertTrue(flight.addPassenger(mike));
        assertEquals(1, flight.getPassengers().size());
        assertEquals("Mike", flight.getPassengers().get(0).getName());

        assertTrue(flight.removePassenger(mike));
        assertEquals(0, flight.getPassengers().size());
    }

    @Test
    void testEconomyFlightWithVipPassenger() {
        Flight flight = new EconomyFlight("2");
        Passenger james = new Passenger("James", true);

        assertTrue(flight.addPassenger(james));
        assertEquals(1, flight.getPassengers().size());
        assertEquals("James", flight.getPassengers().get(0).getName());

        assertFalse(flight.removePassenger(james));
        assertEquals(1, flight.getPassengers().size());
    }
}