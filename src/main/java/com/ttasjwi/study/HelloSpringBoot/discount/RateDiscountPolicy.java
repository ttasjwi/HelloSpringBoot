package com.ttasjwi.study.HelloSpringBoot.discount;

import com.ttasjwi.study.HelloSpringBoot.annotation.MainDiscountPolicy;
import com.ttasjwi.study.HelloSpringBoot.member.Grade;
import com.ttasjwi.study.HelloSpringBoot.member.Member;
import org.springframework.stereotype.Component;

@Component
@MainDiscountPolicy
public class RateDiscountPolicy implements DiscountPolicy {

    private final int DISCOUNT_PERCENT = 10;

    @Override
    public int discountPrice(Member member, int itemPrice) {
        if (member.getGrade() == Grade.VIP) {
            return (itemPrice * DISCOUNT_PERCENT)/100;
        } else {
            return 0;
        }
    }
}
