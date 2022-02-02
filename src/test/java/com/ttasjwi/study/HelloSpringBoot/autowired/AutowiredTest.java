package com.ttasjwi.study.HelloSpringBoot.autowired;

import com.ttasjwi.study.HelloSpringBoot.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    static class TestBean {
        @Autowired(required = false)
        public void setNoBean1(Member noBean1) {
            // required = false ---------> 자동 주입할 스프링 빈이 없으면, 메서드 자체가 호출되지 않음.
            System.out.println("noBean1 = "+noBean1);
        }

        @Autowired
        public void setNoBean2(@Nullable Member noBean2) {
            // @Nullable ---------> 자동 주입할 스프링 빈이 없으면, 호출이 되고 null이 주입됨
            System.out.println("noBean2 = "+noBean2);
        }

        @Autowired
        public void setNoBean3(Optional<Member> noBean3) {
            // Optional<...> ----------------------> 자동 주입할 스프링 빈이 없으면, 호출이 되고 Optional.empty가 주입됨
            System.out.println("noBean3 = "+noBean3);
        }
    }

    @Test
    void autowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }
}
