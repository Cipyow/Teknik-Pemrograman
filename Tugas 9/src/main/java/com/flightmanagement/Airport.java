package com.flightmanagement;

public class Airport {
    public static void main(String[] args) {
        Flight economyFlight = new EconomyFlight("1");
        Flight businessFlight = new BusinessFlight("2");

        Passenger james = new Passenger("James", true);
        Passenger mike = new Passenger("Mike", false);

        economyFlight.addPassenger(james);
        economyFlight.addPassenger(mike);

        businessFlight.addPassenger(james);
        businessFlight.addPassenger(mike);
    }
}