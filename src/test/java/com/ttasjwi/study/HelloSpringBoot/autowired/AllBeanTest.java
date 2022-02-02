package com.ttasjwi.study.HelloSpringBoot.autowired;

import com.ttasjwi.study.HelloSpringBoot.AutoAppConfig;
import com.ttasjwi.study.HelloSpringBoot.discount.DiscountPolicy;
import com.ttasjwi.study.HelloSpringBoot.member.Grade;
import com.ttasjwi.study.HelloSpringBoot.member.Member;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

public class AllBeanTest {

    @Test
    void findAllBeanTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);

        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "memberA", Grade.VIP);
        int fixedDiscountPrice = discountService.discountPrice(member, 10000, "fixedDiscountPolicy");
        int rateDiscountPrice = discountService.discountPrice(member, 20000, "rateDiscountPolicy");

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(discountService).isInstanceOf(DiscountService.class);
        softAssertions.assertThat(fixedDiscountPrice).isEqualTo(1000);
        softAssertions.assertThat(rateDiscountPrice).isEqualTo(2000);
        softAssertions.assertAll();
    }

    static class DiscountService {
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> discountPolicies;

        @Autowired
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> discountPolicies) {
            this.policyMap = policyMap;
            this.discountPolicies = discountPolicies;
            System.out.println("policyMap = " + policyMap);
            System.out.println("discountPolicies = " + discountPolicies);
        }

        public int discountPrice(Member member, int price, String discountPolicyCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountPolicyCode);
            return discountPolicy.discountPrice(member, price);
        }
    }
}
