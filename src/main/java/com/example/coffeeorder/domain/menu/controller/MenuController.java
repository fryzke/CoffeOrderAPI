package com.example.coffeeorder.domain.menu.controller;

import com.example.coffeeorder.domain.menu.dto.MenuResponse;
import com.example.coffeeorder.domain.menu.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/v1/menus")
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;

    @GetMapping
    public List<MenuResponse> getMenus() {
        return menuService.findAllMenus();
    }

    @GetMapping("/popular")
    public List<MenuResponse> getPopularMenus() {
        return menuService.findPopularMenus();
    }
}
