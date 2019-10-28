package com.bizvane.delayer;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Challenger
 */
@Component
@ConditionalOnBean(DelayerBusiness.class)
public class DelayerBusinessManager {

    private static Map<String, DelayerBusiness> map = new HashMap<>();

    public DelayerBusinessManager(List<DelayerBusiness> list){
        for (DelayerBusiness item: list) {
            String type = item.getType();
            if (map.get(type) != null){
                throw new RuntimeException("业务类型冲突：" + type);
            } else {
                map.put(type, item);
            }
        }
    }

    public static DelayerBusiness get(String type){
        return map.get(type);
    }
}
