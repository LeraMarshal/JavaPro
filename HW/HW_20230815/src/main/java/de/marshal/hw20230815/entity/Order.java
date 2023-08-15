package de.marshal.hw20230815.entity;

import java.time.LocalDate;
import java.util.List;

public class Order {
    public static final int DAYS_BEFORE_EXPIRATION = 7;
    private int id;
    private Person customer;
    private List<Product> products;
    private LocalDate orderDate;

    public Order(int id, Person customer, List<Product> products, LocalDate orderDate) {
        this.id = id;
        this.customer = customer;
        this.products = products;
        this.orderDate = orderDate;
    }

    public int getId() {
        return id;
    }

    public Person getCustomer() {
        return customer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public double calculateTotalPrice() {
        double finalPrice = 0;
        for (Product product : products) {
            finalPrice += product.getPrice();
        }
        return finalPrice;
    }

    public int getNumberOfProducts() {
        return products.size();
    }

    public boolean isOrderExpired() {
        return orderDate.isBefore(LocalDate.now().minusDays(DAYS_BEFORE_EXPIRATION));
    }
}
