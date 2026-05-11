package com.example.coffeeorder.domain.order.repository;

import com.example.coffeeorder.domain.order.entity.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o.menuId FROM Order o WHERE o.orderAt >= :startDate GROUP BY o.menuId ORDER BY COUNT(o) DESC")
    List<Long> findPopularMenuIds(LocalDateTime startDate, Pageable pageable);
}
