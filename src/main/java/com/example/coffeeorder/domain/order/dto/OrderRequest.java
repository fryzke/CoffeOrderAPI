package com.example.coffeeorder.domain.order.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderRequest {
    private Long memberId;
    private Long menuId;

    public OrderRequest(Long memberId, Long menuId) {
        this.memberId = memberId;
        this.menuId = menuId;
    }
}
