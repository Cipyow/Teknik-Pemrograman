abstract class Aircraft {
    protected String id;
    protected String model;
    protected int altitude;

    public Aircraft(String id, String model) {
        this.id = id;
        this.model = model;
        this.altitude = 0;
    }

    public abstract void takeOff();
    public abstract void land();
    
    public void setAltitude(int altitude) {
        this.altitude = altitude;
        System.out.println(id + " changed altitude to " + altitude + " feet.");
    }
}
