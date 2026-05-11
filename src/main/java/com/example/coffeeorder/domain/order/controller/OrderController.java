package com.example.coffeeorder.domain.order.controller;

import com.example.coffeeorder.domain.order.dto.OrderRequest;
import com.example.coffeeorder.domain.order.dto.OrderResponse;
import com.example.coffeeorder.domain.order.entity.Order;
import com.example.coffeeorder.domain.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public OrderResponse order(@RequestBody OrderRequest request) {
        Order order = orderService.order(request.getMemberId(), request.getMenuId());
        return new OrderResponse(order);
    }
}
