package com.ttasjwi.study.HelloSpringBoot.filter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.context.annotation.ComponentScan.Filter;

public class ComponentFilterAppConfigTest {


    ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);

    @Configuration
    @ComponentScan(
            includeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
            excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class)
    )
    static class ComponentFilterAppConfig {

    }

    @Test
    @DisplayName("BeanA는 Include시켰으므로 스프링 빈에 등록된다.")
    void filterScan_BeanA() {
        BeanA beanA = ac.getBean("beanA", BeanA.class);
        
        assertThat(beanA).isNotNull();
    }

    @Test
    @DisplayName("BeanB는 exclude시켰으므로 스프링 빈에 등록되지 않는다.")
    void filterScan_BeanB() {
        assertThatThrownBy(
                ()->ac.getBean("beanB", BeanB.class))
                .isInstanceOf(NoSuchBeanDefinitionException.class);
    }
}
