public class AirTrafficManagementSystem {
    public static void main(String[] args) {
        AirTrafficControl atc = new ATCSystem();
        Aircraft boeing737 = new CommercialAircraft("B737", "Boeing 737");
        Aircraft cessna172 = new PrivateAircraft("C172", "Cessna 172");
        
        atc.sendTakeOffPermission(boeing737);
        atc.requestAltitudeChange(boeing737, 30000);
        atc.checkWeatherConditions(boeing737);
        
        atc.sendTakeOffPermission(cessna172);
        atc.requestAltitudeChange(cessna172, 10000);
        atc.checkWeatherConditions(cessna172);
        
        atc.rerouteAircraft(boeing737, "New York Airway B");
        
        atc.sendLandingPermission(boeing737);
        atc.sendLandingPermission(cessna172);
    }
}
