package com.ttasjwi.study.HelloSpringBoot.lifecycle;

public class NetworkClient {

    private String url;

    public NetworkClient() {
        System.out.printf("생성자 호출 -  url = %s%n", url);
        connect();
        call("초기화 연결 메시지");
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
}
