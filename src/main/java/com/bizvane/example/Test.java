package com.bizvane.example;

import com.bizvane.delayer.DelayQueueTask;
import com.bizvane.example.delayerbusiness.DelayCoupon;
import com.bizvane.example.delayerbusiness.DelayOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Calendar;
import java.util.Date;

@Component
public class Test {

    @Autowired
    private DelayCoupon delayCoupon;

    @Autowired
    private DelayOrder delayOrder;

    @PostConstruct
    private void init(){
        DelayQueueTask.add(delayCoupon.getType(), new CouponBO("这是一张券150", null), getDate(150));
        DelayQueueTask.add(delayCoupon.getType(), new CouponBO("这是一张券60", null), getDate(60));
        DelayQueueTask.add(delayCoupon.getType(), new CouponBO("这是一张券120", null), getDate(120));
        DelayQueueTask.add(delayCoupon.getType(), new CouponBO("这是一张券90", null), getDate(90));


        DelayQueueTask.add(delayOrder.getType(), new OrderBO("订单75", null), getDate(75));
        DelayQueueTask.add(delayOrder.getType(), new OrderBO("订单180", null), getDate(180));
        DelayQueueTask.add(delayOrder.getType(), new OrderBO("订单135", null), getDate(135));
        DelayQueueTask.add(delayOrder.getType(), new OrderBO("订单105", null), getDate(105));


    }

    private Date getDate(int second){
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.SECOND, second);
        return nowTime.getTime();
    }
}
