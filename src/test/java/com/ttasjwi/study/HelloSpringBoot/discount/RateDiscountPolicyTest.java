package com.ttasjwi.study.HelloSpringBoot.discount;

import com.ttasjwi.study.HelloSpringBoot.member.Grade;
import com.ttasjwi.study.HelloSpringBoot.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RateDiscountPolicyTest {

    RateDiscountPolicy rateDiscountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP에 대하여 10% 할인이 적용되어야 한다.")
    void if_vip() {
        //given
        Member member =  new Member(1L, "memberVIP", Grade.VIP);

        //when
        int discount = rateDiscountPolicy.discountPrice(member, 15000);

        //then
        assertThat(discount).isEqualTo(1500);
    }

    @Test
    @DisplayName("VIP가 아니면 할인이 적용되지 않는다.")
    void if_not_vip() {
        //given
        Member member =  new Member(2L, "memberBASIC", Grade.BASIC);

        //when
        int discount = rateDiscountPolicy.discountPrice(member, 100000);

        //then
        assertThat(discount).isEqualTo(0);
    }

}