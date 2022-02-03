package com.ttasjwi.study.HelloSpringBoot.web;

import com.ttasjwi.study.HelloSpringBoot.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    private final MyLogger myLogger;

    @RequestMapping("log-demo") // log-demo 요청에 대하여
    @ResponseBody // view 없이 바로 객체(문자열)를 반환
    public String logDemo(HttpServletRequest request) {
        String requestURL = request.getRequestURL().toString();

        System.out.println("myLogger = " + myLogger.getClass());
        //myLogger = class com.ttasjwi.study.HelloSpringBoot.common.MyLogger$$EnhancerBySpringCGLIB$$4785bcf7
        //스프링 내부적으로 MyLogger의 바이트 코드 조작을 거쳐, 상속체를 만듬.
        //상속체가 의존관계 주입이 되고 MyLogger의 프록시 역할을 수행함. (MyLogger을 호출하여, 요청함)

        myLogger.setRequestURL(requestURL);
        myLogger.log("controller test");

        try {
            Thread.sleep(1000); // 각각의 요청마다 독립적인 빈 생성이 되고, 각 빈마다 비즈니스 로직이 수행되는지 확인하기 쉽도록 1초 지연 
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        logDemoService.logic("testId");
        return "OK";
    }

}
