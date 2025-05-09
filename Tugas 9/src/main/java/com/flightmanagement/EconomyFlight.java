package com.flightmanagement;

public class EconomyFlight extends Flight {

    public EconomyFlight(String id) {
        super(id);
    }

    @Override
    public boolean addPassenger(Passenger passenger) {
        passengers.add(passenger);
        return true;
    }

    @Override
    public boolean removePassenger(Passenger passenger) {
        if (!passenger.isVip()) {
            passengers.remove(passenger);
            return true;
        }
        return false;
    }
}