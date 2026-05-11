package com.example.coffeeorder.domain.order.service;

import com.example.coffeeorder.domain.order.dto.OrderPlacedEvent;
import com.example.coffeeorder.domain.order.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DataCollectorService {
    @EventListener
    public void handleOrderPlaced(OrderPlacedEvent event) {
        Order order = event.getOrder();
        log.info("Sending order data to platform: MemberID={}, MenuID={}, Price={}", 
                order.getMemberId(), order.getMenuId(), order.getOrderPrice());
    }
}
