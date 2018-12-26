package com.wangwenjun.concurrent.chapter08.pool;

import com.wangwenjun.concurrent.chapter19.Future;

public class ThreadPoolExecutor implements ExecutorService {

    private final class Worker implements Runnable {

        Runnable firstTask;

        public Worker(Runnable runnable) {
            this.firstTask = runnable;
        }

        @Override
        public void run() {
            runWorker(this);
        }

        private void runWorker(Worker worker) {

        }
    }


    @Override
    public void shutdown() {

    }

    @Override
    public <T> Future<T> submit(Runnable runnable) {
        return null;
    }

    @Override
    public void execute(Runnable runnable) {

    }
}
