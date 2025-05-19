package com.peerislands.order.model;

import java.util.List;
import java.util.UUID;

public class Order {
    private String id;
    private List<String> items;
    private OrderStatus status;

    public Order(List<String> items) {
        this.id = UUID.randomUUID().toString();
        this.items = items;
        this.status = OrderStatus.PENDING;
    }

    public String getId() { return id; }
    public List<String> getItems() { return items; }
    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }
}