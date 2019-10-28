package com.bizvane.example;

import lombok.Getter;

@Getter
public class CouponBO {

    private String code;
    private String name;

    public CouponBO(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
