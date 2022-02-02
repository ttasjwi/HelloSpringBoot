package com.ttasjwi.study.HelloSpringBoot.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

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
