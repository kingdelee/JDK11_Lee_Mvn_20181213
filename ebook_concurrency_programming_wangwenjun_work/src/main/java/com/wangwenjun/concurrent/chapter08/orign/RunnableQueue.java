package com.wangwenjun.concurrent.chapter08.orign;

public interface RunnableQueue {

    void offer(Runnable runnable);

    Runnable take() throws InterruptedException;

    int size();
}
