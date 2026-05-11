package com.example.coffeeorder.domain.menu.dto;

import com.example.coffeeorder.domain.menu.entity.Menu;
import lombok.Getter;

@Getter
public class MenuResponse {
    private final Long id;
    private final String name;
    private final Long price;

    public MenuResponse(Menu menu) {
        this.id = menu.getId();
        this.name = menu.getName();
        this.price = menu.getPrice();
    }
}
