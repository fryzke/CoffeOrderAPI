package com.example.coffeeorder;

import com.example.coffeeorder.domain.menu.entity.Menu;
import com.example.coffeeorder.domain.menu.repository.MenuRepository;
import com.example.coffeeorder.domain.member.entity.Member;
import com.example.coffeeorder.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final MenuRepository menuRepository;
    private final MemberRepository memberRepository;

    @Override
    public void run(String... args) {
        menuRepository.save(new Menu("Americano", 4000L));
        menuRepository.save(new Menu("Latte", 4500L));
        menuRepository.save(new Menu("Cappuccino", 5000L));
        menuRepository.save(new Menu("Mocha", 5500L));
        menuRepository.save(new Menu("Espresso", 3500L));

        memberRepository.save(new Member("User1"));
    }
}
