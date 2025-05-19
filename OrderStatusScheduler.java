package com.peerislands.order.scheduler;

import com.peerislands.order.service.OrderService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class OrderStatusScheduler {

    private final OrderService orderService;

    public OrderStatusScheduler(OrderService orderService) {
        this.orderService = orderService;
    }

    @Scheduled(fixedRate = 300000) // Every 5 minutes
    public void processPendingOrders() {
        orderService.updatePendingOrdersToProcessing();
    }
}