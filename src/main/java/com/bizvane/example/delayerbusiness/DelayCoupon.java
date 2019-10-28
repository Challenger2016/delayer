package com.bizvane.example.delayerbusiness;

import com.bizvane.delayer.DelayerBusiness;
import com.bizvane.example.CouponBO;
import org.springframework.stereotype.Component;

/**
 * 券延时业务
 */
@Component
public class DelayCoupon implements DelayerBusiness<CouponBO>{
    @Override
    public String getType() {
        return "coupon";
    }

    @Override
    public void execute(CouponBO o) {
        System.out.println(o.getCode());
    }

}
