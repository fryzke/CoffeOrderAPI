package com.example.coffeeorder.domain.order.dto;

import com.example.coffeeorder.domain.order.entity.Order;
import lombok.Getter;

@Getter
public class OrderResponse {
    private final Long orderId;
    private final Long memberId;
    private final Long menuId;
    private final Long orderPrice;

    public OrderResponse(Order order) {
        this.orderId = order.getId();
        this.memberId = order.getMemberId();
        this.menuId = order.getMenuId();
        this.orderPrice = order.getOrderPrice();
    }
}
