package com.example.coffeeorder.domain.point.service;

import com.example.coffeeorder.domain.member.entity.Member;
import com.example.coffeeorder.domain.member.repository.MemberRepository;
import com.example.coffeeorder.domain.point.entity.PointHistory;
import com.example.coffeeorder.domain.point.entity.PointType;
import com.example.coffeeorder.domain.point.repository.PointHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PointService {
    private final MemberRepository memberRepository;
    private final PointHistoryRepository pointHistoryRepository;

    @Transactional
    public void chargePoint(Long memberId, Long amount) {
        Member member = memberRepository.findByIdWithLock(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        
        member.chargePoint(amount);
        pointHistoryRepository.save(new PointHistory(memberId, amount, PointType.CHARGE));
    }
}
