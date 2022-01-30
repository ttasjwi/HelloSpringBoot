package com.ttasjwi.study.HelloSpringBoot.order;

import com.ttasjwi.study.HelloSpringBoot.discount.DiscountPolicy;
import com.ttasjwi.study.HelloSpringBoot.member.Member;
import com.ttasjwi.study.HelloSpringBoot.member.MemberRepository;

public class OrderServiceImpl implements OrderService{

    private MemberRepository memberRepository;
    private DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discountPrice(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
