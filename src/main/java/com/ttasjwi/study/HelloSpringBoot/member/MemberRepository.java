package com.ttasjwi.study.HelloSpringBoot.member;

public interface MemberRepository {

    void save(Member member);
    Member findById(Long memberId);
}
