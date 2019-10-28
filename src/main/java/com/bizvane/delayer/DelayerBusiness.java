package com.bizvane.delayer;

/**
 * @author Challenger
 */
public interface DelayerBusiness<T> {

    /**
     * 获取业务类型
     * @return
     */
    String getType();

    /**
     * 执行业务操作
     * @param t
     */
    void execute(T t);
}
