package com.ttasjwi.study.HelloSpringBoot.member;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MemoryMemberRepository implements MemberRepository {

    private static MemoryMemberRepository instance;
    private Map<Long, Member> store = new ConcurrentHashMap<>();

    private MemoryMemberRepository() {}

    public static MemoryMemberRepository getInstance() {
        if (instance == null) {
            instance = new MemoryMemberRepository();
        }
        return instance;
    }

    @Override
    public void save(Member member) {
        store.put(member.getMemberId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
