package com.ttasjwi.study.HelloSpringBoot.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Autowired // 내부적으로 ac.getBean("MemberRepository.class")비슷한 기능으로 빈을 호출해서 의존관계를 주입해줌)
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // For Test : 테스트(스프링 빈의 싱글톤 여부) 목적으로 생성한 구현체 메서드
    public MemberRepository getMemberRepository() {
        return this.memberRepository;
    }
}
