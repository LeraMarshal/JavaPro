public class CarStore {
    private VehicleFactory vehicleFactory;

    public CarStore(VehicleFactory vehicleFactory) {
        this.vehicleFactory = vehicleFactory;
    }

    public Car order(String name) {
        return vehicleFactory.createCar(name);
    }
}
