package com.peerislands.order.controller;

import com.peerislands.order.model.Order;
import com.peerislands.order.model.OrderStatus;
import com.peerislands.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public Order createOrder(@RequestBody List<String> items) {
        return orderService.createOrder(items);
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable String id) {
        return orderService.getOrderById(id);
    }

    @GetMapping
    public List<Order> getAllOrders(@RequestParam(required = false) OrderStatus status) {
        return orderService.getAllOrders(Optional.ofNullable(status));
    }

    @DeleteMapping("/{id}")
    public String cancelOrder(@PathVariable String id) {
        boolean cancelled = orderService.cancelOrder(id);
        return cancelled ? "Order cancelled." : "Cannot cancel order.";
    }
}