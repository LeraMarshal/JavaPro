package Cafe;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;

public class Restaurant {
    /*
  Реализовать логику работы небольшого ресторана, который может принять только 5 человек одновременно.
  Люди прибывают и выстраиваются в очередь на входе, ожидая освобождения столиков.
  Все заказывают комплексный обед из 3-х блюд (суп, салат и второе).
  В ресторане один официант и три повара, каждый из которых готовит только одно блюдо на одного человека.
  Один повар готовит только супы, второй только салаты, третий только второе.
  Официант собирает обед на поднос и относит посетителям.
  Посетители едят и покидают ресторан, освобождая столы для новых посетителей.
   */
    private Semaphore semaphore = new Semaphore(5);
    private LinkedBlockingQueue<List<Dish>> readyDishes = new LinkedBlockingQueue<>();

    private Chef saladChef = new Chef("Salad chef", 1000L);
    private Chef soupChef = new Chef("Soup chef", 2000L);
    private Chef stakeChef = new Chef("Stake chef", 3000L);

    private Waiter waiter = new Waiter("Waiter-1", List.of(saladChef, soupChef, stakeChef), readyDishes);

    public Restaurant() {
        saladChef.start();
        soupChef.start();
        stakeChef.start();
        waiter.start();
    }

    public void clientArrived() throws InterruptedException {
        semaphore.acquire();
    }

    public List<Dish> serve() throws InterruptedException {
        saladChef.newOrder(Dish.SALAD);
        soupChef.newOrder(Dish.SOUP);
        stakeChef.newOrder(Dish.STAKE);

        return readyDishes.take();
    }

    public void clientLeft() {
        semaphore.release();
    }

    public void close() {
        saladChef.interrupt();
        soupChef.interrupt();
        stakeChef.interrupt();

        waiter.interrupt();
    }
}