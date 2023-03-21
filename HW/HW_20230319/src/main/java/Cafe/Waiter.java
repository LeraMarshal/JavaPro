package Cafe;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Waiter extends Thread {
    private List<Chef> chefs;
    private Queue<List<Dish>> readyDishes;

    public Waiter(String name, List<Chef> chefs, Queue<List<Dish>> readyDishes) {
        super(name);

        this.chefs = chefs;
        this.readyDishes = readyDishes;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                List<Dish> dishes = new ArrayList<>();
                for (Chef chef : chefs) {
                    System.out.println(Thread.currentThread().getName() + " is waiting for " + chef.getName());
                    dishes.add(chef.getDish());
                }

                readyDishes.add(dishes);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

