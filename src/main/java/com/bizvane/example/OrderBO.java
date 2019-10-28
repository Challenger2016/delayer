package com.bizvane.example;

import lombok.Getter;

@Getter
public class OrderBO {

    private String orderNo;
    private String orderTime;

    public OrderBO(String orderNo, String orderTime) {
        this.orderNo = orderNo;
        this.orderTime = orderTime;
    }
}
