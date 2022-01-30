package com.ttasjwi.study.HelloSpringBoot.discount;

import com.ttasjwi.study.HelloSpringBoot.member.Grade;
import com.ttasjwi.study.HelloSpringBoot.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FixedDiscountPolicyTest {

    FixedDiscountPolicy fixedDiscountPolicy = new FixedDiscountPolicy();

    @Test
    @DisplayName("VIP는 1000원 고정금액 할인이 적용되어야 한다.")
    void if_vip() {
        //given
        Member member =  new Member(1L, "memberVIP", Grade.VIP);

        //when
        int discount = fixedDiscountPolicy.discountPrice(member, 10000);

        //then
        assertThat(discount).isEqualTo(1000);
    }

    @Test
    @DisplayName("VIP가 아니면 1000원 고정금액 할인이 적용되지 않는다.")
    void if_not_vip() {
        //given
        Member member =  new Member(2L, "memberBASIC", Grade.BASIC);

        //when
        int discount = fixedDiscountPolicy.discountPrice(member, 10000);

        //then
        assertThat(discount).isEqualTo(0);
    }
}