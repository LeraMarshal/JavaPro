package Cafe;

import java.util.concurrent.LinkedBlockingQueue;

public class Chef extends Thread {
    private long duration;

    private LinkedBlockingQueue<Dish> orders = new LinkedBlockingQueue<>();

    private LinkedBlockingQueue<Dish> readyDishes = new LinkedBlockingQueue<>();

    public Chef(String name, long duration) {
        super(name);

        this.duration = duration;
    }

    public void newOrder(Dish dish) throws InterruptedException {
        orders.put(dish);
    }

    public Dish getDish() throws InterruptedException {
        return readyDishes.take();
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Dish order = orders.take();
                System.out.println(Thread.currentThread().getName() + " is making " + order);
                Thread.sleep(duration);

                System.out.println(Thread.currentThread().getName() + " has made " + order);
                readyDishes.add(order);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
