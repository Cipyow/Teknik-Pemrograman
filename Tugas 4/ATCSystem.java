class ATCSystem implements AirTrafficControl {
    @Override
    public void sendLandingPermission(Aircraft aircraft) {
        System.out.println("ATC: Landing permission granted to " + aircraft.id);
        aircraft.land();
    }

    @Override
    public void sendTakeOffPermission(Aircraft aircraft) {
        System.out.println("ATC: Takeoff permission granted to " + aircraft.id);
        aircraft.takeOff();
    }
    
    @Override
    public void requestAltitudeChange(Aircraft aircraft, int newAltitude) {
        System.out.println("ATC: Approving altitude change for " + aircraft.id + " to " + newAltitude + " feet.");
        aircraft.setAltitude(newAltitude);
    }
    
    @Override
    public void rerouteAircraft(Aircraft aircraft, String newRoute) {
        System.out.println("ATC: Rerouting " + aircraft.id + " to " + newRoute);
    }
    
    @Override
    public void checkWeatherConditions(Aircraft aircraft) {
        String[] conditions = {"Clear", "Rainy", "Stormy", "Foggy", "Windy"};
        String weather = conditions[(int) (Math.random() * conditions.length)];
        System.out.println("ATC: Current weather for " + aircraft.id + " is " + weather);
        
        if (weather.equals("Stormy")) {
            System.out.println("ATC: Warning! Storm detected. Rerouting " + aircraft.id);
            rerouteAircraft(aircraft, "Alternative Route due to storm");
        } else if (weather.equals("Foggy")) {
            System.out.println("ATC: Visibility is low. Reducing altitude for " + aircraft.id);
            requestAltitudeChange(aircraft, 5000);
        }
    }
}
