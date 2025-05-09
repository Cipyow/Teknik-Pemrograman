package com.flightmanagement;

import java.util.*;

public abstract class Flight {
    private String id;
    protected List<Passenger> passengers = new ArrayList<>();

    public Flight(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public abstract boolean addPassenger(Passenger passenger);
    public abstract boolean removePassenger(Passenger passenger);
}