interface AirTrafficControl {
    void sendLandingPermission(Aircraft aircraft);
    void sendTakeOffPermission(Aircraft aircraft);
    void requestAltitudeChange(Aircraft aircraft, int newAltitude);
    void rerouteAircraft(Aircraft aircraft, String newRoute);
    void checkWeatherConditions(Aircraft aircraft);
}
