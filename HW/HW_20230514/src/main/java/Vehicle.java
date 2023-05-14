public abstract class Vehicle {
    private String name;
    private int wheels;

    public Vehicle(String name, int wheels) {
        this.name = name;
        this.wheels = wheels;
    }

    public String getName() {
        return name;
    }

    public int getWheels() {
        return wheels;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "name='" + name + '\'' +
                ", wheels=" + wheels +
                '}';
    }
}
