package com.ttasjwi.study.HelloSpringBoot;

import com.ttasjwi.study.HelloSpringBoot.member.Grade;
import com.ttasjwi.study.HelloSpringBoot.member.Member;
import com.ttasjwi.study.HelloSpringBoot.member.MemberService;
import com.ttasjwi.study.HelloSpringBoot.order.Order;
import com.ttasjwi.study.HelloSpringBoot.order.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {

    public static void main(String[] args) {

        // 스프링 컨테이너 생성
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        // MemberService, OrderService 구현체 받아오기
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);

        // 회원 가입
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        // 주문
        Order order = orderService.createOrder(memberId, "itemA", 30000);

        // 출력 (주문 및 실 지불 금액)
        System.out.printf("order = %s%n", order.toString());
        System.out.printf("order.calculatePrice = %d%n", order.calculatePrice());
    }
}
