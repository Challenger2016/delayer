package com.bizvane.delayer;

import com.bizvane.es.canal.common.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.Manager;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;

/**
 * @author Challenger
 */
@Slf4j
@Component
@ConditionalOnBean(DelayerBusiness.class)
public class DelayQueueTask {

    private static BlockingQueue<DelayerTask<?>> task = new DelayQueue<>();

    private static boolean start = false;

    public static <T> void add(String type, T t, Date date){
        if (!start){
            throw new RuntimeException("DelayQueueTask没有注册业务处理类");
        }

        if (DelayerBusinessManager.get(type) == null){
            throw new RuntimeException("DelayQueueTask此业务类型不存在");
        }
        DelayerTask<T> delayerTask = new DelayerTask(type, t, date.getTime());
        try {
            task.put(delayerTask);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    public void start() {
        start = true;
        new Thread(() -> {
            for (;;) {
                DelayerTask take = null;
                try {
                    take = task.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (take != null){
                    String type = take.getType();
                    Object object = take.getT();

                    DelayerBusiness busType = DelayerBusinessManager.get(type);
                    busType.execute(object);
                }
            }
        }).start();

    }
}
