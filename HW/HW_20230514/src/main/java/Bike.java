public  class Bike extends Vehicle{
    public Bike(String name) {
        super(name, 2);
    }

    @Override
    public String toString() {
        return "Bike{} " + super.toString();
    }
}
