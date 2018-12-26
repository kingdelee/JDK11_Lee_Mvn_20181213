package com.wangwenjun.concurrent.chapter08.test1;


import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestClient1 {

    public static void main(String[] args) {
        LinkedBlockingQueue<Runnable> queue =
                new LinkedBlockingQueue<Runnable>(3);
        RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardPolicy();

        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 5, 60, TimeUnit.SECONDS, queue, handler);
        for (int i = 0; i < 9; i++) {
            threadPool.execute(new Thread(new ThreadPoolTest(), "Thread".concat(i + "")));
            System.out.println("线程池中活跃的线程数： " + threadPool.getPoolSize());
            if (queue.size() > 0) {
                System.out.println("----------------队列中阻塞的线程数" + queue.size());
            }
        }
        threadPool.shutdown();
    }

}
