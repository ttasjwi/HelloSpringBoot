package com.ttasjwi.study.HelloSpringBoot.discount;

import com.ttasjwi.study.HelloSpringBoot.member.Grade;
import com.ttasjwi.study.HelloSpringBoot.member.Member;
import org.springframework.stereotype.Component;

@Component
public class FixedDiscountPolicy implements DiscountPolicy{

    private final int FIXED_DISCOUNT_AMOUNT = 1000;

    @Override
    public int discountPrice(Member member, int itemPrice) {
        if (member.getGrade() == Grade.VIP) {
            return FIXED_DISCOUNT_AMOUNT;
        } else {
            return 0;
        }
    }
}
