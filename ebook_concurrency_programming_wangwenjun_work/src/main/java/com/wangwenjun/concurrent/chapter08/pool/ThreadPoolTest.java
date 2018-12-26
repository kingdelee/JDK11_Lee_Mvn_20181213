package com.wangwenjun.concurrent.chapter08.pool;

public class ThreadPoolTest implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}