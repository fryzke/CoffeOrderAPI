package com.example.coffeeorder.domain.point.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class PointHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long memberId;

    private Long amount;

    @Enumerated(EnumType.STRING)
    private PointType type;

    private LocalDateTime createdAt;

    public PointHistory(Long memberId, Long amount, PointType type) {
        this.memberId = memberId;
        this.amount = amount;
        this.type = type;
        this.createdAt = LocalDateTime.now();
    }
}
