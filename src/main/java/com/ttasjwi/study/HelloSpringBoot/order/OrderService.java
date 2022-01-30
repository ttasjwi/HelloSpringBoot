package com.ttasjwi.study.HelloSpringBoot.order;

public interface OrderService {

    Order createOrder(Long memberId, String itemName, int itemPrice);
}
