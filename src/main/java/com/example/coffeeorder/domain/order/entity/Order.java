package com.example.coffeeorder.domain.order.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long memberId;

    private Long menuId;

    private Long orderPrice;

    private LocalDateTime orderAt;

    public Order(Long memberId, Long menuId, Long orderPrice) {
        this.memberId = memberId;
        this.menuId = menuId;
        this.orderPrice = orderPrice;
        this.orderAt = LocalDateTime.now();
    }
}
