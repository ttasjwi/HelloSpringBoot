package com.ttasjwi.study.HelloSpringBoot.web;

import com.ttasjwi.study.HelloSpringBoot.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoService {

    private final ObjectProvider<MyLogger> myLoggerProvider;
    //private final MyLogger myLogger; // MyLogger을 바로 주입해버리면, 오류가 발생함 (MyLogger의 빈 스코프는 Request임...)

    public void logic(String id) {
        MyLogger myLogger = myLoggerProvider.getObject();
        myLogger.log("service id = " + id);
    }
}
