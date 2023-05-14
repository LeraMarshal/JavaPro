public  class Car extends Vehicle {
    public Car(String name) {
        super(name, 4);
    }

    @Override
    public String toString() {
        return "Car{} " + super.toString();
    }
}
