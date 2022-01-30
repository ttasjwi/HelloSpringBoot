package com.ttasjwi.study.HelloSpringBoot;

import com.ttasjwi.study.HelloSpringBoot.member.Grade;
import com.ttasjwi.study.HelloSpringBoot.member.Member;
import com.ttasjwi.study.HelloSpringBoot.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {
        
        // 스프링 컨테이너 생성
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        
        // MemberService 구현체 받아오기
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        // 회원 가입
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        // 회원 조회
        Member findMember = memberService.findMember(1L);
        
        // 출력
        System.out.printf("new Member = %s%n", member.getName());
        System.out.printf("find Member = %s%n", findMember.getName());
    }
}
