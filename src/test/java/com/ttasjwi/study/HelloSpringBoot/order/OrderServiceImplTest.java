package com.ttasjwi.study.HelloSpringBoot.order;

import com.ttasjwi.study.HelloSpringBoot.discount.FixedDiscountPolicy;
import com.ttasjwi.study.HelloSpringBoot.member.Grade;
import com.ttasjwi.study.HelloSpringBoot.member.Member;
import com.ttasjwi.study.HelloSpringBoot.member.MemoryMemberRepository;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OrderServiceImplTest {

    @Test
    void createOrder() {
        //given
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "memberA", Grade.VIP));

        //when
        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, new FixedDiscountPolicy());
        Order order = orderService.createOrder(1L, "itemA", 10000);

        //then
        assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}