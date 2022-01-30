package com.ttasjwi.study.HelloSpringBoot;

import com.ttasjwi.study.HelloSpringBoot.discount.RateDiscountPolicy;
import com.ttasjwi.study.HelloSpringBoot.member.MemberService;
import com.ttasjwi.study.HelloSpringBoot.member.MemberServiceImpl;
import com.ttasjwi.study.HelloSpringBoot.member.MemoryMemberRepository;
import com.ttasjwi.study.HelloSpringBoot.order.OrderService;
import com.ttasjwi.study.HelloSpringBoot.order.OrderServiceImpl;

public class AppConfig {

    private static AppConfig instatce;

    private AppConfig() {}

    public static AppConfig getInstance() {
        if (instatce == null) {
            instatce = new AppConfig();
        }
        return instatce;
    }

    public MemberService memberService() {
        return new MemberServiceImpl(MemoryMemberRepository.getInstance());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(MemoryMemberRepository.getInstance(), new RateDiscountPolicy());
    }
}
