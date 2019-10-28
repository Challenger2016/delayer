package com.bizvane.example.delayerbusiness;

import com.bizvane.delayer.DelayerBusiness;
import com.bizvane.example.OrderBO;
import org.springframework.stereotype.Component;

/**
 * 限时订单延时业务
 */
@Component
public class DelayOrder implements DelayerBusiness<OrderBO> {
    @Override
    public String getType() {
        return "order";
    }

    @Override
    public void execute(OrderBO o) {
        System.out.println(o.getOrderNo());
    }

}
