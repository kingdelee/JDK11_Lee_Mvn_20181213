package com.wangwenjun.concurrent.chapter08.test2;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestClient2 {

    static Logger logger = LogManager.getLogger("root");

    public static void main(String[] args) {
        LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>(3);
        RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardPolicy();


        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 5, 60, TimeUnit.SECONDS, queue, handler);


    }

    /**
     * 当work数小于init值时
     */
    @Test
    public void t1() throws InterruptedException {
        LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>(12);
        RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardPolicy();

        // core = 3, max = 5, keepliveTime 60, queue = link
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 8, 60, TimeUnit.SECONDS, queue, handler);
        log(threadPool);
        for (int i = 0; i < 20; i++) {
            threadPool.execute(new Task(i));
        }
        log(threadPool);
        TimeUnit.SECONDS.sleep(20);
        log(threadPool);
        TimeUnit.SECONDS.sleep(20);
        log(threadPool);

        TimeUnit.MINUTES.sleep(100);
        threadPool.shutdown();

    }

    public static void log(ThreadPoolExecutor threadPool) {
        logger.info("--------------------");
        logger.info("getActiveCount:" + threadPool.getActiveCount());
        logger.info("getCompletedTaskCount:" + threadPool.getCompletedTaskCount());
        logger.info("getCorePoolSize:" + threadPool.getCorePoolSize());
        logger.info("getLargestPoolSize:" + threadPool.getLargestPoolSize());
        logger.info("getMaximumPoolSize:" + threadPool.getMaximumPoolSize());
        logger.info("getPoolSize:" + threadPool.getPoolSize());
        logger.info("getTaskCount:" + threadPool.getTaskCount());
        logger.info("getQueue:" + threadPool.getQueue().size());

    }

}


class Task implements Runnable {

    private int i;

    public Task(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        try {
            System.out.println("thread run:" + i);
            TimeUnit.SECONDS.sleep(10);
            System.out.println("thread end:" + i);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}