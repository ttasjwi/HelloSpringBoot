package com.ttasjwi.study.HelloSpringBoot.filter;


import java.lang.annotation.*;

@Target(ElementType.TYPE) // 타입 지정
@Retention(RetentionPolicy.RUNTIME) // 어노테이션이 지정되는 범위 지정
@Documented
public @interface MyIncludeComponent {
}
