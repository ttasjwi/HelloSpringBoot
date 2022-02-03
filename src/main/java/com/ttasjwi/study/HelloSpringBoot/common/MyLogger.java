package com.ttasjwi.study.HelloSpringBoot.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.annotation.RequestScope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
//@RequestScope : 이거로 대체해서 써도 될 듯? (https://www.baeldung.com/spring-bean-scopes)
@Scope (
        value = WebApplicationContext.SCOPE_REQUEST, // == "request"
        proxyMode = ScopedProxyMode.TARGET_CLASS
)
public class MyLogger {

    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.printf("[%s][%s]{%s}%n", this.uuid, this.requestURL, message);
    }

    @PostConstruct // 빈 생성 후 초기화
    public void init() {
        this.uuid = UUID.randomUUID().toString();
        System.out.printf("[%s]{request scope bean create : %s}%n", this.uuid, this);
    }

    @PreDestroy // 빈 소멸 전
    public void close() {
        System.out.printf("[%s]{request scope bean close : %s}%n", this.uuid, this);
    }

}
