package com.ttasjwi.study.HelloSpringBoot.discount;

import com.ttasjwi.study.HelloSpringBoot.member.Member;

public interface DiscountPolicy {

    int discountPrice(Member member, int itemPrice);
}
