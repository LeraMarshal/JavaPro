public class Main {
    /*
     * - Создать абстрактный класс(контракт) для автосалона. Придумать общие свойства для автомобилей
     * - Реализовать несколько конкретных классов автомобилей.
     * - Сделать класс для управления автосалоном
     * - Добавить в систему интерфейс. Вынести в него контракт для создания автомобилей и мотоциклов
     */
    public static void main(String[] args) {
        VehicleFactoryImpl vehicleFactory = new VehicleFactoryImpl();
        System.out.println(vehicleFactory.createCar("BMWx5"));
        System.out.println(vehicleFactory.createBike("Motox5"));

        CarStore carStore = new CarStore(vehicleFactory);
        System.out.println(carStore.order("AudiA3"));
    }
}
