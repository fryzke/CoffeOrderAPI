package com.example.coffeeorder.domain.point.controller;

import com.example.coffeeorder.domain.point.dto.PointChargeRequest;
import com.example.coffeeorder.domain.point.service.PointService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/points")
@RequiredArgsConstructor
public class PointController {
    private final PointService pointService;

    @PostMapping("/charge")
    public void charge(@RequestBody PointChargeRequest request) {
        pointService.chargePoint(request.getMemberId(), request.getAmount());
    }
}
