package de.marshal.hw20230815.service;

import de.marshal.cw20230815.entity.Order;
import de.marshal.cw20230815.entity.Product;
import de.marshal.cw20230815.exceprtion.InvalidOrderException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class OrderProcessor {
    private List<Order> orders = new ArrayList<>();

    public OrderProcessor() {
    }

    public void addOrder(Order order) throws InvalidOrderException {
        if (order == null || order.getProducts() == null || order.getOrderDate() == null) {
            throw new InvalidOrderException();
        }

        Map<Product, Long> map = order.getProducts().stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        for (Map.Entry<Product, Long> entry : map.entrySet()) {
            if (entry.getKey().getQuantity() < entry.getValue()) {
                throw new InvalidOrderException();
            }
        }

        orders.add(order);
    }

    public double calculateTotalRevenue() {
        return orders.stream()
                .mapToDouble(Order::calculateTotalPrice)
                .sum();
    }

    public List<Order> getExpiredOrders() {
        return orders.stream()
                .filter(Order::isOrderExpired)
                .collect(Collectors.toList());
    }
}
