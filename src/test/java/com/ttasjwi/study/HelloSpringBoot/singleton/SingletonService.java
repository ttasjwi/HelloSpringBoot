package com.ttasjwi.study.HelloSpringBoot.singleton;

public class SingletonService {

    private static final SingletonService instance = new SingletonService();

    private SingletonService() {}

    public static SingletonService getInstance() {
        return instance;
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직을 호출했다.");
    }
}
