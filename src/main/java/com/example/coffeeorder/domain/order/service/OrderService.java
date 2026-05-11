package com.example.coffeeorder.domain.order.service;

import com.example.coffeeorder.domain.member.entity.Member;
import com.example.coffeeorder.domain.member.repository.MemberRepository;
import com.example.coffeeorder.domain.menu.entity.Menu;
import com.example.coffeeorder.domain.menu.repository.MenuRepository;
import com.example.coffeeorder.domain.order.dto.OrderPlacedEvent;
import com.example.coffeeorder.domain.order.entity.Order;
import com.example.coffeeorder.domain.order.repository.OrderRepository;
import com.example.coffeeorder.domain.point.entity.PointHistory;
import com.example.coffeeorder.domain.point.entity.PointType;
import com.example.coffeeorder.domain.point.repository.PointHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final MemberRepository memberRepository;
    private final MenuRepository menuRepository;
    private final OrderRepository orderRepository;
    private final PointHistoryRepository pointHistoryRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public Order order(Long memberId, Long menuId) {
        Member member = memberRepository.findByIdWithLock(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 메뉴입니다."));
        
        member.usePoint(menu.getPrice());
        pointHistoryRepository.save(new PointHistory(memberId, menu.getPrice(), PointType.USE));
        
        Order order = orderRepository.save(new Order(memberId, menuId, menu.getPrice()));
        
        eventPublisher.publishEvent(new OrderPlacedEvent(order));
        
        return order;
    }
}
