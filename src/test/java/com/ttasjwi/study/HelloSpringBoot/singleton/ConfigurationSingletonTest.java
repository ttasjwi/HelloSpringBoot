package com.ttasjwi.study.HelloSpringBoot.singleton;

import com.ttasjwi.study.HelloSpringBoot.AppConfig;
import com.ttasjwi.study.HelloSpringBoot.discount.DiscountPolicy;
import com.ttasjwi.study.HelloSpringBoot.discount.RateDiscountPolicy;
import com.ttasjwi.study.HelloSpringBoot.member.MemberRepository;
import com.ttasjwi.study.HelloSpringBoot.member.MemberService;
import com.ttasjwi.study.HelloSpringBoot.member.MemberServiceImpl;
import com.ttasjwi.study.HelloSpringBoot.member.MemoryMemberRepository;
import com.ttasjwi.study.HelloSpringBoot.order.OrderService;
import com.ttasjwi.study.HelloSpringBoot.order.OrderServiceImpl;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class ConfigurationSingletonTest {

    @Test
    @DisplayName("Configuration 어노테이션 기반으로 생성된 스프링 빈은 싱글톤이여야한다.")
    void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);

        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);
        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        System.out.println("memberRepository = "+memberRepository);
        System.out.println("memberService -> memberRepository1 = " + memberRepository1);
        System.out.println("orderService -> memberRepository2 = " + memberRepository2);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(memberRepository).isSameAs(memberRepository1);
        softAssertions.assertThat(memberRepository).isSameAs(memberRepository2);
        softAssertions.assertAll();
    }

    static class NotConfig {

        @Bean
        MemberService memberService() {
            return new MemberServiceImpl(memberRepository());
        }

        @Bean
        OrderService orderService() {
            return new OrderServiceImpl(memberRepository(), discountPolicy());
        }

        @Bean
        MemberRepository memberRepository() {
            return new MemoryMemberRepository();
        }

        @Bean
        DiscountPolicy discountPolicy() {
            return new RateDiscountPolicy();
        }
    }

    @Test
    @DisplayName("Configuration 어노테이션이 붙지 않은 config 으로 컨테이너 생성시 싱글톤이 보장되지 않는다.")
    void not_ConfigurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(NotConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);

        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);
        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        System.out.println("memberRepository = "+memberRepository);
        System.out.println("memberService -> memberRepository1 = " + memberRepository1);
        System.out.println("orderService -> memberRepository2 = " + memberRepository2);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(memberRepository).isNotSameAs(memberRepository1);
        softAssertions.assertThat(memberRepository).isNotSameAs(memberRepository2);
        softAssertions.assertAll();
    }
}
