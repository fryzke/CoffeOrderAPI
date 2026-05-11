package com.example.coffeeorder.domain.menu.service;

import com.example.coffeeorder.domain.menu.dto.MenuResponse;
import com.example.coffeeorder.domain.menu.entity.Menu;
import com.example.coffeeorder.domain.menu.repository.MenuRepository;
import com.example.coffeeorder.domain.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;
    private final OrderRepository orderRepository;

    @Transactional(readOnly = true)
    public List<MenuResponse> findAllMenus() {
        return menuRepository.findAll().stream()
                .map(MenuResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MenuResponse> findPopularMenus() {
        LocalDateTime sevenDaysAgo = LocalDateTime.now().minusDays(7);
        List<Long> popularMenuIds = orderRepository.findPopularMenuIds(sevenDaysAgo, PageRequest.of(0, 3));
        
        List<Menu> menus = menuRepository.findAllById(popularMenuIds);
        return popularMenuIds.stream()
                .map(id -> menus.stream().filter(m -> m.getId().equals(id)).findFirst().orElseThrow())
                .map(MenuResponse::new)
                .collect(Collectors.toList());
    }
}
