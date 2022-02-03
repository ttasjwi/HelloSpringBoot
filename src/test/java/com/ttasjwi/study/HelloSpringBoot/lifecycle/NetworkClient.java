package com.ttasjwi.study.HelloSpringBoot.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class NetworkClient implements InitializingBean, DisposableBean {

    private String url;

    public NetworkClient() {
        System.out.printf("생성자 호출 -  url = %s%n", url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스 시작 시 호출
    public void connect() {
        System.out.printf("connect : %s%n",this.url);
    }

    public void call(String message) {
        System.out.printf("call : %s, message = %s%n", this.url, message );
    }

    public void disconnect() {
        System.out.printf("close : %s%n",this.url);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("NetworkClient.afterPropertiesSet");
        connect();
        call("초기화 연결 메시지");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("NetworkClient.destroy");
        disconnect();
    }
}
