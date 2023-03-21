package Cafe;

import java.util.List;

public class Client extends Thread {
    private Restaurant restaurant;

    public Client(String name, Restaurant restaurant) {
        super(name);

        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " is waiting to enter restaurant");
            restaurant.clientArrived();

            System.out.println(Thread.currentThread().getName() + " is waiting to get served");
            List<Dish> dishes = restaurant.serve();

            System.out.println(Thread.currentThread().getName() + " is eating " + dishes);
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + " is leaving restaurant");
            restaurant.clientLeft();
        }
    }
}