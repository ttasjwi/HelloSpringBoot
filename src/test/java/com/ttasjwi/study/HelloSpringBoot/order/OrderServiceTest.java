package com.ttasjwi.study.HelloSpringBoot.order;

import com.ttasjwi.study.HelloSpringBoot.AppConfig;
import com.ttasjwi.study.HelloSpringBoot.member.Grade;
import com.ttasjwi.study.HelloSpringBoot.member.Member;
import com.ttasjwi.study.HelloSpringBoot.member.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

class OrderServiceTest {

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    void beforeEach() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        this.memberService = applicationContext.getBean("memberService", MemberService.class);
        this.orderService = applicationContext.getBean("orderService", OrderService.class);
    }

    @Test
    @DisplayName("VIP일 때 10% 할인이 주문정보에 정상적으로 반영되어야한다.")
    void createOrderTest_vip() {
        //given
        Long memberId = 1L;
        Member member = new Member(memberId, "memberVIP", Grade.VIP);
        memberService.join(member);

        //when
        Order order = orderService.createOrder(memberId, "itemA", 30000);

        //then
        assertThat(order.getDiscountPrice()).isEqualTo(3000);
    }

    @Test
    @DisplayName("VIP가 아닐 때 할인금액이 없도록 주문정보에 정상적으로 반영되어야한다.")
    void createOrderTest_not_vip() {
        //given
        Long memberId = 2L;
        Member member = new Member(memberId, "memberBASIC", Grade.BASIC);
        memberService.join(member);

        //when
        Order order = orderService.createOrder(memberId, "itemB", 15000);

        //then
        assertThat(order.getDiscountPrice()).isEqualTo(0);
    }
}