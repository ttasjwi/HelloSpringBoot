package com.ttasjwi.study.HelloSpringBoot.member;

public interface MemberService {
    void join(Member member);
    Member findMember(Long memberId);
}
