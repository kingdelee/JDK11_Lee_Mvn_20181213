package com.wangwenjun.concurrent.chapter08.orign;

public interface ThreadFactory {
    Thread createThread(Runnable runnable);
}
