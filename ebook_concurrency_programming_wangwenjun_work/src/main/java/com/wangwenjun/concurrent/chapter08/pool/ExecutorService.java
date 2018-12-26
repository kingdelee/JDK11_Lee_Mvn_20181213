package com.wangwenjun.concurrent.chapter08.pool;

import com.wangwenjun.concurrent.chapter19.Future;

public interface ExecutorService extends Executor {

    void shutdown();

    <T> Future<T> submit(Runnable runnable);

}
