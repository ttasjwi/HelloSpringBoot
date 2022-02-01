package com.ttasjwi.study.HelloSpringBoot.scan;

import com.ttasjwi.study.HelloSpringBoot.AutoAppConfig;
import com.ttasjwi.study.HelloSpringBoot.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class AutoAppConfigTest {

    @Test
    @DisplayName("컴포넌트 스캔에 의한 빈 등록, Autowired에 의한 의존관계 주입이 정상적으로 작동되는지 테스트")
    void basicScan() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        MemberService bean = ac.getBean(MemberService.class);

        assertThat(bean).isInstanceOf(MemberService.class);
    }
}
