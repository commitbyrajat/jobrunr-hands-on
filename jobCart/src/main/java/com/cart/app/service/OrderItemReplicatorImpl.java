package com.cart.app.service;

import org.springframework.stereotype.Component;

@Component
public class OrderItemReplicatorImpl implements OrderItemReplicator {
    @Override
    public void process(String orderItem) {
        System.out.println("Processing:::"+orderItem);
    }
}
