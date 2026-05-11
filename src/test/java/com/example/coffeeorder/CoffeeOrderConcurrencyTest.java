package com.example.coffeeorder;

import com.example.coffeeorder.domain.member.entity.Member;
import com.example.coffeeorder.domain.member.repository.MemberRepository;
import com.example.coffeeorder.domain.menu.entity.Menu;
import com.example.coffeeorder.domain.menu.repository.MenuRepository;
import com.example.coffeeorder.domain.order.service.OrderService;
import com.example.coffeeorder.domain.point.service.PointService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CoffeeOrderConcurrencyTest {

    @Autowired
    private PointService pointService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Test
    @DisplayName("동시에 100번의 포인트 충전 요청이 올 경우 정확히 충전되어야 한다.")
    void chargePointConcurrency() throws InterruptedException {
        // given
        Member member = memberRepository.save(new Member("Tester"));
        int threadCount = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch latch = new CountDownLatch(threadCount);

        // when
        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try {
                    pointService.chargePoint(member.getId(), 1000L);
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await();

        // then
        Member updatedMember = memberRepository.findById(member.getId()).orElseThrow();
        assertThat(updatedMember.getPoint()).isEqualTo(100000L);
    }

    @Test
    @DisplayName("동시에 10번의 주문 요청이 올 경우 포인트가 정확히 차감되어야 한다.")
    void orderConcurrency() throws InterruptedException {
        // given
        Member member = memberRepository.save(new Member("Tester2"));
        pointService.chargePoint(member.getId(), 50000L);
        Menu menu = menuRepository.save(new Menu("Latte", 5000L));

        int threadCount = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(threadCount);

        // when
        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try {
                    orderService.order(member.getId(), menu.getId());
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await();

        // then
        Member updatedMember = memberRepository.findById(member.getId()).orElseThrow();
        assertThat(updatedMember.getPoint()).isEqualTo(0L);
    }
}
