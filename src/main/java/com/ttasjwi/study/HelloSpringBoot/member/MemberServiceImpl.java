package com.ttasjwi.study.HelloSpringBoot.member;

public class MemberServiceImpl implements MemberService{

    private MemberRepository memberRepository = MemoryMemberRepository.getInstance();

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
