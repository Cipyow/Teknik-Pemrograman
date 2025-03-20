class CommercialAircraft extends Aircraft {
    public CommercialAircraft(String id, String model) {
        super(id, model);
    }

    @Override
    public void takeOff() {
        System.out.println("Commercial Aircraft " + id + " is taking off.");
    }

    @Override
    public void land() {
        System.out.println("Commercial Aircraft " + id + " is landing.");
    }
}
