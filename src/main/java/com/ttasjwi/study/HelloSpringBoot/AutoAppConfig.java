package com.ttasjwi.study.HelloSpringBoot;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.springframework.context.annotation.ComponentScan.*;

@Configuration
@ComponentScan (
        excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = Configuration.class)) // 기존의 Configuration 달린 클래스들을 스프링 빈에 등록하지 않도록 함. (기존 클래스들을 남기기 위함)
public class AutoAppConfig {

}
