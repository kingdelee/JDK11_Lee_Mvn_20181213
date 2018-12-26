package com.wangwenjun.concurrent.chapter08.pool;

public interface Executor {

    void execute(Runnable runnable);

}
