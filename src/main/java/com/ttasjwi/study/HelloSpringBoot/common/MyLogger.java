package com.ttasjwi.study.HelloSpringBoot.common;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
@Scope(value = "request") // 빈스코프 : request 스코프(http 요청마다 하나당 생성 및 요청이 끝날 때 소멸)
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
