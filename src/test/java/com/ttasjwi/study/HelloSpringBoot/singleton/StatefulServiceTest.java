package com.ttasjwi.study.HelloSpringBoot.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {
    
    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean("statefulService", StatefulService.class);
        StatefulService statefulService2 = ac.getBean("statefulService", StatefulService.class);
        
        // ThreadA : '땃쥐' 고객이 20000원 어치 상품을 구매함.
        statefulService1.order("땃쥐", 20000);
        
        // ThreadB : '땃고양이' 고객이 30000원 어치 상품을 구매함
        statefulService2.order("땃고양이", 30000);
        
        // 땃쥐 사용자의 지불 금액이 변경됨.
        int price = statefulService1.getPrice();
        System.out.println("price = " + price);
    }
    
    static class TestConfig {
        
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}