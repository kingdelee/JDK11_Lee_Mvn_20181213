package com.wangwenjun.concurrent.chapter05;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeoutException;

import static java.lang.System.currentTimeMillis;
import static java.lang.Thread.currentThread;

public class BooleanLock implements Lock {

    private Thread currentThread;

    private boolean locked = false;

    private final List<Thread> blockedList = new ArrayList<>();

    /**
     * 加锁
     *
     * @throws InterruptedException
     */
    @Override
    public void lock() throws InterruptedException {
        synchronized (this) {
            while (locked) {
                // 如果已经锁了，就将当前线程添加到阻塞队列中
                if (!blockedList.contains(currentThread()))
                    blockedList.add(currentThread());
                // 等待并释放monitor的lock
                this.wait();
            }

            // 还未上锁，则将阻塞队列中删除该线程
            blockedList.remove(currentThread());
            // 上锁
            this.locked = true;
            this.currentThread = currentThread();
        }
    }

    @Override
    public void lock(long mills) throws InterruptedException, TimeoutException {
        synchronized (this) {
            if (mills <= 0) {
                this.lock();
            } else {
                long remainingMills = mills;
                long endMills = currentTimeMillis() + remainingMills;
                while (locked) {
                    if (remainingMills <= 0)
                        throw new TimeoutException("can not get the lock during " + mills + " ms.");
                    if (!blockedList.contains(currentThread()))
                        blockedList.add(currentThread());
                    this.wait(remainingMills);
                    // 线程超时设置
                    remainingMills = endMills - currentTimeMillis();
                }

                blockedList.remove(currentThread());
                this.locked = true;
                this.currentThread = currentThread();
            }
        }
    }

    @Override
    public void unlock() {
        synchronized (this) {
            if (currentThread == currentThread()) {
                this.locked = false;
                Optional.of(currentThread().getName() + " release the lock monitor.")
                        .ifPresent(System.out::println);
                this.notifyAll();
            }
        }
    }

    @Override
    public List<Thread> getBlockedThreads() {
        return Collections.unmodifiableList(blockedList);
    }
}