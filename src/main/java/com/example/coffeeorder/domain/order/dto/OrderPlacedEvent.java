package com.example.coffeeorder.domain.order.dto;

import com.example.coffeeorder.domain.order.entity.Order;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class OrderPlacedEvent {
    private final Order order;
}
