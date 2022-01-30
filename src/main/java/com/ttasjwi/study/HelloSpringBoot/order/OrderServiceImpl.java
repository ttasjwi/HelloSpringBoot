package com.ttasjwi.study.HelloSpringBoot.order;

import com.ttasjwi.study.HelloSpringBoot.discount.DiscountPolicy;
import com.ttasjwi.study.HelloSpringBoot.discount.RateDiscountPolicy;
import com.ttasjwi.study.HelloSpringBoot.member.Member;
import com.ttasjwi.study.HelloSpringBoot.member.MemberRepository;
import com.ttasjwi.study.HelloSpringBoot.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository = MemoryMemberRepository.getInstance();
    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discountPrice(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
