package com.wangwenjun.concurrent.chapter08.test1;

import java.util.concurrent.TimeUnit;

public class ThreadPoolTest implements Runnable {
    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}