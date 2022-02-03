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
    private final ObjectProvider<MyLogger> myLoggerProvider;
    //private final MyLogger myLogger; // MyLogger을 바로 주입해버리면, 오류가 발생함 (MyLogger의 빈 스코프는 Request임...)

    @RequestMapping("log-demo") // log-demo 요청에 대하여
    @ResponseBody // view 없이 바로 객체(문자열)를 반환
    public String logDemo(HttpServletRequest request) {
        String requestURL = request.getRequestURL().toString();

        MyLogger myLogger = myLoggerProvider.getObject(); // 요청당 1개의 MyLogger 빈 호출이 보장됨(빈 스코프 : request)
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
