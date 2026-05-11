package com.example.coffeeorder.domain.member.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long point;

    public Member(String name) {
        this.name = name;
        this.point = 0L;
    }

    public void chargePoint(Long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("충전 금액은 0보다 커야 합니다.");
        }
        this.point += amount;
    }

    public void usePoint(Long amount) {
        if (this.point < amount) {
            throw new IllegalStateException("잔액이 부족합니다.");
        }
        this.point -= amount;
    }
}
