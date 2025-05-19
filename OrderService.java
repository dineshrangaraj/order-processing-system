package com.peerislands.order.service;

import com.peerislands.order.model.Order;
import com.peerislands.order.model.OrderStatus;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final Map<String, Order> orderStore = new HashMap<>();

    public Order createOrder(List<String> items) {
        Order order = new Order(items);
        orderStore.put(order.getId(), order);
        return order;
    }

    public Order getOrderById(String id) {
        return orderStore.get(id);
    }

    public List<Order> getAllOrders(Optional<OrderStatus> status) {
        return status.map(s ->
            orderStore.values().stream().filter(o -> o.getStatus() == s).collect(Collectors.toList())
        ).orElse(new ArrayList<>(orderStore.values()));
    }

    public boolean cancelOrder(String id) {
        Order order = orderStore.get(id);
        if (order != null && order.getStatus() == OrderStatus.PENDING) {
            orderStore.remove(id);
            return true;
        }
        return false;
    }

    public void updatePendingOrdersToProcessing() {
        orderStore.values().stream()
            .filter(order -> order.getStatus() == OrderStatus.PENDING)
            .forEach(order -> order.setStatus(OrderStatus.PROCESSING));
    }
}