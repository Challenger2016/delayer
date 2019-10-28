package com.bizvane.delayer;

import lombok.Data;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author Challenger
 */
@Data
public class DelayerTask<T> implements Delayed {

    private String type;
    private T t;
    private long runningTime;
    public DelayerTask(String type, T t, long runningTime) {
        this.runningTime = runningTime;
        this.type = type;
        this.t = t;
    }

    @Override
    public int compareTo(Delayed o) {
        if (this.getDelay(TimeUnit.MICROSECONDS) < o.getDelay(TimeUnit.MICROSECONDS)) {
            return -1;
        } else if (this.getDelay(TimeUnit.MICROSECONDS) > o.getDelay(TimeUnit.MICROSECONDS)) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(runningTime - System.currentTimeMillis(), TimeUnit.MICROSECONDS);
    }

    @Override
    public String toString() {
        return "" + runningTime;
    }
    }