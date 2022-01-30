package com.ttasjwi.study.HelloSpringBoot.member;

import com.ttasjwi.study.HelloSpringBoot.AppConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MemberServiceTest {

    MemberService memberService;

    @BeforeEach
    void beforeEace() {
        AppConfig appConfig = AppConfig.getInstance();
        this.memberService = appConfig.memberService();
    }

    @Test
    @DisplayName("회원을 id로 조회했을 때 정합성에 맞게 회원이 조회되어야한다.")
    void join() {
        Member member = new Member(1L, "memberA", Grade.VIP);

        //when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then
        assertThat(member).isEqualTo(findMember);
    }

}