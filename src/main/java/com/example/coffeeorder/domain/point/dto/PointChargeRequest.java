package com.example.coffeeorder.domain.point.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PointChargeRequest {
    private Long memberId;
    private Long amount;

    public PointChargeRequest(Long memberId, Long amount) {
        this.memberId = memberId;
        this.amount = amount;
    }
}
