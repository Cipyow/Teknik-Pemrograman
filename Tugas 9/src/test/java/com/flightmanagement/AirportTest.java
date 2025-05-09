package com.flightmanagement;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class AirportTest {

    private Flight economyFlight;
    private Flight businessFlight;
    private Passenger james;
    private Passenger mike;

    @BeforeEach
    void setUp() {
        economyFlight = new EconomyFlight("1");
        businessFlight = new BusinessFlight("2");
        james = new Passenger("James", true);
        mike = new Passenger("Mike", false);
    }

    @Test
    void testEconomyFlight() {
        assertTrue(economyFlight.addPassenger(mike));
        assertTrue(economyFlight.removePassenger(mike));

        assertTrue(economyFlight.addPassenger(james));
        assertFalse(economyFlight.removePassenger(james));
    }

    @Test
    void testBusinessFlight() {
        assertFalse(businessFlight.addPassenger(mike));
        assertFalse(businessFlight.removePassenger(mike));

        assertTrue(businessFlight.addPassenger(james));
        assertFalse(businessFlight.removePassenger(james));
    }
}