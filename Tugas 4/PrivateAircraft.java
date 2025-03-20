class PrivateAircraft extends Aircraft {
    public PrivateAircraft(String id, String model) {
        super(id, model);
    }

    @Override
    public void takeOff() {
        System.out.println("Private Aircraft " + id + " is taking off.");
    }

    @Override
    public void land() {
        System.out.println("Private Aircraft " + id + " is landing.");
    }
}
