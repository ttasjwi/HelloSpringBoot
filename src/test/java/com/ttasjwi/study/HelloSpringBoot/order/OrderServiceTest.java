package com.ttasjwi.study.HelloSpringBoot.order;

import com.ttasjwi.study.HelloSpringBoot.member.Grade;
import com.ttasjwi.study.HelloSpringBoot.member.Member;
import com.ttasjwi.study.HelloSpringBoot.member.MemberService;
import com.ttasjwi.study.HelloSpringBoot.member.MemberServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OrderServiceTest {

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    void beforeEach() {
        this.memberService = new MemberServiceImpl();
        this.orderService = new OrderServiceImpl();
    }

    @Test
    @DisplayName("VIP일 때 1000원 할인이 주문정보에 정상적으로 반영되어야한다.")
    void createOrderTest_vip() {
        //given
        Long memberId = 1L;
        Member member = new Member(memberId, "memberVIP", Grade.VIP);
        memberService.join(member);

        //when
        Order order = orderService.createOrder(memberId, "itemA", 10000);

        //then
        assertThat(order.getDiscountPrice()).isEqualTo(1000);
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