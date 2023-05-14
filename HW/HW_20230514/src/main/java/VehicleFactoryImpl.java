public class VehicleFactoryImpl implements VehicleFactory{
    @Override
    public Car createCar(String name) {
        return new Car(name);
    }

    @Override
    public Bike createBike(String name) {
        return new Bike(name);
    }
}
